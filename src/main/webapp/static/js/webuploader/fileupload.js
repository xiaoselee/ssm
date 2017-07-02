function fileUpLoad() {
	this.panel = undefined;
}

fileUpLoad.prototype = new Object();
fileUpLoad.prototype.constructor = "fileUpLoad";

//暴漏的初始化接口
fileUpLoad.prototype.setInitInfo = function() {
	this.panel = $("body").find('#uploaderdiv');
	this.wrap = "#uploader";
	this.createInfo = {
		pick: {
			id: '#filePickerDiv',
			label: '点击选择图片'
		},
		dnd: '#uploader .queueList',
		paste: document.body,
/*		accept: {
			title: 'Images',
			extensions: 'gif,jpg,jpeg,bmp,png',
			mimeTypes: 'image/*'
		},*/
		// swf文件路径
		swf: '' + 'webuploader/0.1.5/Uploader.swf',
		disableGlobalDnd: true,
		chunked: true,
		// server: 'http://webuploader.duapp.com/server/fileupload.php',
		server: 'http://2betop.net/fileupload.php',
		fileNumLimit: 300,
		fileSizeLimit: 5 * 1024 * 1024, // 200 M
		fileSingleSizeLimit: 1 * 1024 * 1024 // 50 M
	};
};

fileUpLoad.prototype.lanch = function() {
	this.setInitInfo();
	this._init();
	this._setOnBind();
	this.$upload.addClass('state-' + this.state);
	updateTotalProgress(this);
}

fileUpLoad.prototype.appenToDiv = function(){
	var html = $('<div id="uploader"><div class="queueList"><div id="dndArea" class="placeholder">'+
		'<div id="filePickerDiv"></div><p>或将文件拖到这里，单次最多可选300张</p>'+
		'</div></div><div class="statusBar" style="display:none;"><div class="progress">'+
		'<span class="text">0%</span><span class="percentage"></span>'+
		'</div><div class="info"></div><div class="btns"><div id="filePicker2"></div>'+
		'<div class="uploadBtn">开始上传</div></div></div></div>');
		this.panel.append(html);
}

fileUpLoad.prototype._init = function() {
	this.appenToDiv();
	this._wrap = this.panel.find(this.wrap);
	this.$queue = $('<ul class="filelist"></ul>');
	this.$queue.appendTo(this._wrap.find('.queueList'));
	// 状态栏，包括进度和控制按钮
	this.$statusBar = this._wrap.find('.statusBar');
		// 文件总体选择信息。
		this.$info = this.$statusBar.find('.info');
		// 上传按钮
		this.$upload = this._wrap.find('.uploadBtn');
		// 没选择文件之前的内容。
		this.$placeHolder = this._wrap.find('.placeholder');
		// 总体进度条
		this.$progress = this.$statusBar.find('.progress').hide();
		// 添加的文件数量
		this.fileCount = 0,
		// 添加的文件总大小
		this.fileSize = 0;
		// 优化retina, 在retina下这个值是2
		var ratio = window.devicePixelRatio || 1;
		// 缩略图大小
		this.thumbnailWidth = 110 * ratio,
		this.thumbnailHeight = 110 * ratio,
		// 可能有pedding, ready, uploading, confirm, done.
		this.state = 'pedding',
		// 所有文件的进度信息，key为file id
		this.percentages = {},
		this.supportTransition = (function() {
			var s = document.createElement('p').style,
				r = 'transition' in s ||
				'WebkitTransition' in s ||
				'MozTransition' in s ||
				'msTransition' in s ||
				'OTransition' in s;
			s = null;
			return r;
		})();
		// WebUploader实例
		this.uploader = undefined;
	if(!WebUploader.Uploader.support()) {
		alert('Web Uploader 不支持您的浏览器！如果你使用的是IE浏览器，请尝试升级 flash 播放器');
		throw new Error('WebUploader does not support the browser you are using.');
	}
	// 实例化
	this.uploader = WebUploader.create(this.createInfo);
	// 添加“添加文件”的按钮，
	this.uploader.addButton({
		id: '#filePicker2',
		label: '继续添加'
	});
};

fileUpLoad.prototype._setOnBind = function() {
	this.onUploadProgress();
	this.onFileQueued();
	this.onFileDequeued();
	this.onError();
	this.bindOn();
};

fileUpLoad.prototype.onUploadProgress = function() {
	var _this = this;
	this.uploader.onUploadProgress = function(file, percentage) {
		var $li = $('#' + file.id);
			$percent = $li.find('.progress span');

		$percent.css('width', percentage * 100 + '%');
		_this.percentages[file.id][1] = percentage;
		updateTotalProgress(_this);
	};
}

fileUpLoad.prototype.onFileQueued = function() {
	var _this = this;
	this.uploader.onFileQueued = function(file) {
		_this.fileCount++;
		_this.fileSize += file.size;
		if(_this.fileCount === 1) {
			_this.$placeHolder.addClass('element-invisible');
			_this.$statusBar.show();
		}
		addFile(file,_this);
		setState('ready',_this);
		updateTotalProgress(_this);
	};
}

fileUpLoad.prototype.onFileDequeued = function() {
		var _this = this;
	this.uploader.onFileDequeued = function(file) {
		_this.fileCount--;
		_this.fileSize -= file.size;
		if(!_this.fileCount) {
			setState('pedding',_this);
		}
		removeFile(file,_this);
		updateTotalProgress(_this);
	};
}

fileUpLoad.prototype.onError = function() {
	var _this = this;
	this.uploader.onError = function(code) {
		alert('Eroor: ' + code);
	};
}

fileUpLoad.prototype.bindOn = function() {
	var _this = this;
	this.uploader.on('all', function(type) {
		var stats;
		switch(type) {
			case 'uploadFinished':
				setState('confirm',_this);
				break;

			case 'startUpload':
				setState('uploading',_this);
				break;

			case 'stopUpload':
				setState('paused',_this);
				break;

		}
	});

	this.$upload.on('click', function() {
		if($(_this).hasClass('disabled')) {
			return false;
		}

		if(_this.state === 'ready') {
			_this.uploader.upload();
		} else if(_this.state === 'paused') {
			_this.uploader.upload();
		} else if(_this.state === 'uploading') {
			_this.uploader.stop();
		}
	});

	this.$info.on('click', '.retry', function() {
		_this.uploader.retry();
	});

	this.$info.on('click', '.ignore', function() {
		alert('todo');
	});
}

//函数声明
// 当有文件添加进来时执行，负责view的创建
function addFile(file, _this) {
	var $li = $('<li id="' + file.id + '">' +
			'<p class="title">' + file.name + '</p>' +
			'<p class="imgWrap"></p>' +
			'<p class="progress"><span></span></p>' +
			'</li>');

		var $btns = $('<div class="file-panel">' +
			'<span class="cancel">删除</span>' +
			'<span class="rotateRight">向右旋转</span>' +
			'<span class="rotateLeft">向左旋转</span></div>').appendTo($li);
		var $prgress = $li.find('p.progress span');
		var $wrap = $li.find('p.imgWrap');
		var $info = $('<p class="error"></p>');

		showError = function(code) {
			switch(code) {
				case 'exceed_size':
					_this.text = '文件大小超出';
					break;

				case 'interrupt':
					_this.text = '上传暂停';
					break;

				default:
					_this.text = '上传失败，请重试';
					break;
			}

			$info.text(_this.text).appendTo($li);
		};

	if(file.getStatus() === 'invalid') {
		showError(file.statusText);
	} else {
		// @todo lazyload
		$wrap.text('预览中');
		_this.uploader.makeThumb(file, function(error, src) {
			if(error) {
				$wrap.text('不能预览');
				return;
			}

			var img = $('<img src="' + src + '">');
			$wrap.empty().append(img);
		}, _this.thumbnailWidth, _this.thumbnailHeight);

		_this.percentages[file.id] = [file.size, 0];
		file.rotation = 0;
	}

	file.on('statuschange', function(cur, prev) {
		if(prev === 'progress') {
			$prgress.hide().width(0);
		} else if(prev === 'queued') {
			$li.off('mouseenter mouseleave');
			$btns.remove();
		}

		// 成功
		if(cur === 'error' || cur === 'invalid') {
			console.log(file.statusText);
			showError(file.statusText);
			_this.percentages[file.id][1] = 1;
		} else if(cur === 'interrupt') {
			showError('interrupt');
		} else if(cur === 'queued') {
			_this.percentages[file.id][1] = 0;
		} else if(cur === 'progress') {
			$info.remove();
			$prgress.css('display', 'block');
		} else if(cur === 'complete') {
			$li.append('<span class="success"></span>');
		}

		$li.removeClass('state-' + prev).addClass('state-' + cur);
	});

	$li.on('mouseenter', function() {
		$btns.stop().animate({
			height: 30
		});
	});

	$li.on('mouseleave', function() {
		$btns.stop().animate({
			height: 0
		});
	});

	$btns.on('click', 'span', function() {
		var index = $(this).index(),
			deg;

		switch(index) {
			case 0:
				_this.uploader.removeFile(file);
				return;

			case 1:
				file.rotation += 90;
				break;

			case 2:
				file.rotation -= 90;
				break;
		}

		if(_this.supportTransition) {
			deg = 'rotate(' + file.rotation + 'deg)';
			$wrap.css({
				'-webkit-transform': deg,
				'-mos-transform': deg,
				'-o-transform': deg,
				'transform': deg
			});
		} else {
			$wrap.css('filter', 'progid:DXImageTransform.Microsoft.BasicImage(rotation=' + (~~((file.rotation / 90) % 4 + 4) % 4) + ')');
		}

	});

	$li.appendTo(_this.$queue);
}

// 负责view的销毁
function removeFile(file,_this) {
	var $li = $('#' + file.id);
	delete _this.percentages[file.id];
	updateTotalProgress(_this);
	$li.off().find('.file-panel').off().end().remove();
}

function updateTotalProgress(_this) {
	var loaded = 0,
		total = 0,percent,
		spans = _this.$progress.children();
	$.each(_this.percentages, function(k, v) {
		total += v[0];
		loaded += v[0] * v[1];
	});

	percent = total ? _this.loaded / total : 0;

	spans.eq(0).text(Math.round(percent * 100) + '%');
	spans.eq(1).css('width', Math.round(percent * 100) + '%');
	updateStatus(_this);
}

function updateStatus(_this) {
	var text = '',
		stats;

	if(_this.state === 'ready') {
		text = '选中' + _this.fileCount + '个文件，共' +
			WebUploader.formatSize(_this.fileSize) + '。';
	} else if(_this.state === 'confirm') {
		stats = _this.uploader.getStats();
		if(stats.uploadFailNum) {
			text = '已成功上传' + stats.successNum + '个文件，' +
				stats.uploadFailNum + '个文件上传失败，<a class="retry" href="#">重新上传</a>失败文件或<a class="ignore" href="#">忽略</a>'
		}

	} else {
		stats = _this.uploader.getStats();
		text = '共' + _this.fileCount + '个（' +
			WebUploader.formatSize(_this.fileSize) +
			'），已上传' + stats.successNum + '个';

		if(stats.uploadFailNum) {
			text += '，失败' + stats.uploadFailNum + '个';
		}
	}

	_this.$info.html(text);
}

function setState(val,_this) {
	var file, stats;

	if(val === _this.state) {
		return;
	}

	_this.$upload.removeClass('state-' + _this.state);
	_this.$upload.addClass('state-' + val);
	_this.state = val;

	switch(_this.state) {
		case 'pedding':
			_this.$placeHolder.removeClass('element-invisible');
			_this.$queue.parent().removeClass('filled');
			_this.$queue.hide();
			_this.$statusBar.addClass('element-invisible');
			_this.uploader.refresh();
			break;

		case 'ready':
			_this.$placeHolder.addClass('element-invisible');
			$('#filePicker2').removeClass('element-invisible');
			_this.$queue.parent().addClass('filled');
			_this.$queue.show();
			_this.$statusBar.removeClass('element-invisible');
			_this.uploader.refresh();
			break;

		case 'uploading':
			$('#filePicker2').addClass('element-invisible');
			_this.$progress.show();
			_this.$upload.text('暂停上传');
			break;

		case 'paused':
			_this.$progress.show();
			_this.$upload.text('继续上传');
			break;

		case 'confirm':
			_this.$progress.hide();
			_this.$upload.text('开始上传').addClass('disabled');

			stats = _this.uploader.getStats();
			if(stats.successNum && !stats.uploadFailNum) {
				setState('finish',_this);
				return;
			}
			break;
		case 'finish':
			stats = _this.uploader.getStats();
			if(stats.successNum) {
				alert('上传成功');
			} else {
				// 没有成功的图片，重设
				_this.state = 'done';
				location.reload();
			}
			break;
	}
	updateStatus(_this);
}