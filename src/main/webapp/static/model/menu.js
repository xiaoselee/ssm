function menu() {

}
menu.prototype = new SimplePage();
menu.prototype.constructor = "menu";

menu.prototype.lanch = function() {
	this.addUrl = "../menu/add";
	this.editUrl = "../menu/update";
	this.panel = $('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};

menu.prototype.setTableInfo = function() {
	var _this = this;
	return {
		url: "../menu/list"
	}
}

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
			align: 'left'
		}]
	];
	return a;
}

menu.prototype.beforOpenAddDialog = function() {
	var dialog = this.addDialog;
	dialog.find("form").form('clear');
	dialog.find('input[name=remarks]').textbox();
}