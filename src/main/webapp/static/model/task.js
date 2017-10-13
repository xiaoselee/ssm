function task() {
	this.addUrl = "../task/add";
	this.editUrl = "../task/update";
	this.listUrl =  "../task/list";
}
task.prototype = new SimplePage();
task.prototype.constructor = "task";

/*menu.prototype.lanch = function() {
	this.panel = $('#contant');
	this.table = this.panel.find('#tt');
	this.init();
};*/

task.prototype.setTableColumnsarray = function() {
	var a = [];
	a = [
		[{
			field: 'id',
			title: 'id',
			width: 50,
			align: 'left'
		},{
			field: 'jobId',
			title: 'jobId',
			width: 50,
			align: 'left'
		}, {
			field: 'jobName',
			title: '任务名称',
			width: 50,
			align: 'left'
		},{
			field: 'jobStatus',
			title: '状态',
			width: 50,
			align: 'left',
			formatter: function(value,row,index){
				if (value == '0'){
					return "未启动";
				} else {
					return "启动";
				}
			}
		}, {
			field: 'cronExpression',
			title: '表达式',
			width: 100,
			align: 'left'
		},{
			field: 'className',
			title: '类路径',
			width: 200,
			align: 'left'
		}, {
			field: 'desc',
			title: '说明',
			width: 100,
			align: 'left'
		}]
	];
	return a;
}

task.prototype.beforOpenAddDialog = function() {
	alert('open add')
}

task.prototype.beforOpenEditDialog = function(row) {
	
}