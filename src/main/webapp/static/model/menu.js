function menu() {
	this.addUrl = "../menu/add";
	this.editUrl = "../menu/update";
	this.listUrl =  "../menu/list";
}
menu.prototype = new SimplePage();
menu.prototype.constructor = "menu";

/*menu.prototype.lanch = function() {
	this.panel = $('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};*/

menu.prototype.setTableColumnsarray = function() {
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
			field: 'icon',
			title: '图标',
			width: 50,
			align: 'left'
		}, {
			field: 'url',
			title: '页面地址',
			width: 200,
			align: 'left'
		}, {
			field: 'status',
			title: '是否启用',
			width: 100,
			align: 'left',
			formatter: function(value,row,index){
				if (value == '0'){
					return "是";
				} else {
					return "否";
				}
			}
		}]
	];
	return a;
}

menu.prototype.beforOpenAddDialog = function() {
	alert('open add')
}

menu.prototype.beforOpenEditDialog = function(row) {
	
}