function SimplePage() {
	this.addUrl = "";
	this.editUrl = "";
	this.listUrl = "";
}

SimplePage.prototype = new Object();
SimplePage.prototype.constructor = "SimplePage";

SimplePage.prototype.lanch = function() {
	this.panel = Easy.getSelected();//$('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};

SimplePage.prototype.init = function() {
	var _this = this;
	this._setTableInfo();
	this.extendTableInfo(_this.setTableInfo());
	this.initDialog();
	this.table = this.table.datagrid(this.getTableInfo());
};

/**
 * 对表格信息的扩展或者替代
 * 需返回一个属性对象
 */
SimplePage.prototype.setTableInfo = function() {
	return {};
};

SimplePage.prototype.extendTableInfo = function(obj) {
	var _this = this;
	$.extend(_this.tableInfo, obj);
};

SimplePage.prototype._setTableInfo = function() {
	var _this = this;
	this.tableInfo = {
		height: 340,
		url: _this.listUrl,
		method: 'GET',
		striped: true,
		fitColumns: true,
		singleSelect: true,
		rownumbers: true,
		pagination: false,
		nowrap: false,
		pagination: true,
		pageSize: 10,
		pageList: [10, 20, 30],
		showFooter: true,
		toolbar: [{
			iconCls: 'icon-add',
			handler: _this.addButtonHandler(_this)
		}, '-', {
			iconCls: 'icon-edit',
			handler: _this.editButtonHandler(_this)
		}],
		columns: _this.setTableColumnsarray(),
		onBeforeLoad: function(param) {},
		onLoadSuccess: function(data) {},
		onLoadError: function() {},
		onClickCell: function(rowIndex, field, value) {}
	};
};

SimplePage.prototype.getTableInfo = function() {
	return this.tableInfo;
};

/**
 * 根据表格的不同，设置不同的表头字段
 * 返回一个数组
 */
SimplePage.prototype.setTableColumnsarray = function() {
	var a = [
		[{
			field: 'id',
			title: 'id',
			width: 180,
			align: 'left'
		}, {
			field: 'name',
			title: 'name',
			width: 150,
			align: 'left'
		}]
	];
	return a;
};

SimplePage.prototype.initDialog = function() {
	var _this = this;
	var addDialog = new AddDialog();
	var addPanel = this.panel.find('#add');
	addDialog.init(addPanel, this.addUrl);
	this.addDialog = addDialog.getDialog();
	
	var editDialog = new EditDialog();
	var editPanel = this.panel.find('#edit');
	editDialog.init(editPanel, this.editUrl);
	this.editDialog = editDialog.getDialog();
}

SimplePage.prototype.addButtonHandler = function(_this) {
	var _f = function() {
		_this._beforOpenAddDialog();
		_this.addDialog.dialog("open");
	}
	return _f;
};

SimplePage.prototype.editButtonHandler = function(_this) {
	var _f = function() {
	var row = _this.table.datagrid('getSelected');
	console.log(row);
	if(row == null || row == 'null') return;
		_this._beforOpenEditDialog(row);
		_this.editDialog.dialog("open");
	}
	return _f;
};

SimplePage.prototype._beforOpenAddDialog = function() {
	var dialog = this.addDialog;
	dialog.find("form").form('clear');
	this.beforOpenAddDialog();
}

SimplePage.prototype.beforOpenAddDialog = function() {

}

SimplePage.prototype._beforOpenEditDialog = function(row) {
	this.editDialog.find('form').form('load',row);
	this.beforOpenEditDialog(row);
}

SimplePage.prototype.beforOpenEditDialog = function(row) {
}

function pageDialog() {
	this.dialog = undefined;
	this.select = undefined;
	this.submitUrl = undefined;
	this.titile = "信息";
};

pageDialog.prototype = new Object();
pageDialog.prototype.constructor = "pageDialog";

pageDialog.prototype.init = function(select, submitUrl) {
	this._init();
	this.addDialogButton();
	this._extendInfo();
	this.select = select;
	this.submitUrl = submitUrl;
	this.dialog = this.select.dialog(this.dialogInfo);
};

pageDialog.prototype._init = function() {
	var _this = this;
	this.dialogInfo = {
		title: _this.title?_this.title:'My Dialog',
		width: 600,
		height: 400,
		closed: true,
		cache: false,
		//href: 'get_content.php',
		modal: true
	}
};

pageDialog.prototype._extendInfo = function() {
	var _this = this;
	$.extend(_this.dialogInfo, _this.extendInfo);
};

pageDialog.prototype.extendInfo = function() {
	return {};
};

pageDialog.prototype.addDialogButton = function() {
	var _this = this;
	this.dialogInfo.buttons = [{
		text: '关闭',
		handler: function() {
			_this.dialog.dialog("close");
		}
	}]
};

pageDialog.prototype.getDialog = function() {
	return this.dialog;
};

function AddDialog() {
	this.title = "添加";
}

AddDialog.prototype = new pageDialog();
AddDialog.prototype.constructor = "AddDialog";

AddDialog.prototype.addDialogButton = function() {
	var _this = this;
	this.dialogInfo.buttons = [{
		text: '保存',
		handler: _this.dialogSaveButtonHandler(_this)
	}, {
		text: '取消',
		handler: function() {
			_this.dialog.dialog("close");
		}
	}];
};

AddDialog.prototype.dialogSaveButtonHandler = function(_this) {
	var _f = function() {
		$.messager.progress({
				interval: 150
			})
			// hide progress bar while the form is invalid
		_this.dialog.find('form').form('submit', {
			url: _this.submitUrl,
			onSubmit: function() {
				var isValid = $(this).form('validate');
				if(!isValid) {
					// hide progress bar while the form is invalid
					$.messager.progress('close');
					$.messager.alert("输入的数据有吴");
				}
				return isValid; // return false will stop the form submission
			},
			success: function(data) {
				// hide progress bar while submit successfully
				_this._dialogSubmitSuccess(data);
			}
		});
	}
	return _f;
};

AddDialog.prototype._dialogSubmitSuccess = function(data) {
	$.messager.progress('close');
	this.dialogSubmitSuccess(data);
}

AddDialog.prototype.dialogSubmitSuccess = function(data) {
	var _this = this;
	data = JSON.parse(data);
	if(data.type == "0") {
		_this.dialog.dialog("close");
		$.messager.alert('提示', data.message);
	} else {
		$.messager.alert('提示', data.message);
	}

}

function EditDialog(){
	this.title = "修改";
}

EditDialog.prototype = new AddDialog();
EditDialog.prototype.constructor = "EditDialog";

var Easy = {
	
}

Easy.getSelected = function(){
	var tab = $('#tt').tabs('getSelected');
	return tab;
}
