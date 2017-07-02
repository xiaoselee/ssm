function role() {

}
role.prototype = new SimplePage();
role.prototype.constructor = "role";

role.prototype.lanch = function() {
	this.addUrl = "../role/add";
	this.editUrl = "../role/update";
	this.panel = $('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};

role.prototype.setTableInfo = function() {
	var _this = this;
	return {
		url: "../role/list"
	}
}

role.prototype.setTableColumnsarray = function() {
	var a = [];
	a = [
		[{
			field: 'id',
			title: 'id',
			width: 50,
			align: 'left'
		}, {
			field: 'name',
			title: 'name',
			width: 100,
			align: 'left'
		},{
			field: 'remarks',
			title: '备注',
			width: 200,
			align: 'left'
		}, {
			field: 'createtime',
			title: '创建时间',
			width: 100,
			align: 'left'
		}, {
			field: 'updatetime',
			title: '修改时间',
			width: 100,
			align: 'left'
		}]
	];
	return a;
}

role.prototype.beforOpenAddDialog = function() {
	var dialog = this.addDialog;
	dialog.find("form").form('clear');
	dialog.find('input[name=remarks]').textbox();
}