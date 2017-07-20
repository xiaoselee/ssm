function user() {
	this.addUrl = "../user/add";
	this.editUrl = "../user/update";
	this.listUrl =  "../user/list";
}
user.prototype = new SimplePage();
user.prototype.constructor = "user";

/*user.prototype.lanch = function() {
	this.panel = $('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};*/

user.prototype.setTableColumnsarray = function() {
	var a = [];
	a = [
		[{
			field: 'id',
			title: 'id',
			width: 50,
			align: 'left'
		}, {
			field: 'name',
			title: '用户名',
			width: 100,
			align: 'left'
		},{
			field: 'password',
			title: '密码',
			width: 50,
			align: 'left'
		}]
	];
	return a;
}

user.prototype.beforOpenAddDialog = function() {
	//alert('open add')
}

user.prototype.beforOpenEditDialog = function(row) {
	
}