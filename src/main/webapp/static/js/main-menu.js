
			function getMenuHtml(o) {
				var _html = '';
				if(o.sunList == null || o.sunList.length == 0) {
					_html = '<li><a href="#" url="'+o.url+'" class="active tablink"><i class="lnr ' + o.icon + '"></i> <span>' + o.name + '</span></a></li>';
				} else {
					_html += '<li>' +
						'<a href="#subPages' + o.id + '" data-toggle="collapse" class="collapsed"><i class="lnr ' + o.icon + '"></i> <span>' + o.name + '</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>' +
						'<div id="subPages' + o.id + '" class="collapse ">' +
						'<ul class="nav">';
					for(var i = 0; i < o.sunList.length; i++) {
						_html += getMenuHtml2(o.sunList[i]);
					}
					_html += '</ul>' +
						'</div>' +
						'</li>';
				}
				return _html;
			}

			function getMenuHtml2(o) {
				var _html = '';
				if(o.sunList == null || o.sunList.length == 0) {
					_html = '<li><a href="#" url="' + o.url + '" class="tablink">' + o.name + '</a></li>';
				} else {
					_html += '<li>' +
						'<a href="#subPages' + o.id + '" data-toggle="collapse" class="collapsed"><i class="lnr ' + o.icon + '"></i> <span>' + o.name + '</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>' +
						'<div id="subPages' + o.id + '" class="collapse ">' +
						'<ul class="nav">';
					for(var i = 0; i < o.sunList.length; i++) {
						_html += getMenuHtml2(o.sunList[i]);
					}
					_html += '</ul>' +
						'</div>' +
						'</li>';
				}
				return _html;
			}