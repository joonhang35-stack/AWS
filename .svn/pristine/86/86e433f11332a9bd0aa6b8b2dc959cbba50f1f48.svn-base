/*$(document).ready(function() {
	if (window.screen.availWidth <= 1200) document.body.style.zoom="80%";
});*/

function getScreenWidth() {
	return (window.screen.availWidth - 50);
}

function clearDataTableSort(id) {
	var a = $("#" + id + " thead tr:nth-child(2) th").siblings();
	a.removeClass("ui-state-active");
	a.find('.ui-sortable-column-icon').removeClass('ui-icon-triangle-1-s ui-icon-triangle-1-n');
}
function checkDrawDate(date){
	
	var invalidDates = document.getElementById('idForm:idDrawDateList').value;
	var invalidDatesArray = invalidDates.split(",");
    var day=date.getDate();
    var month = date.getMonth()+1;
    var year = date.getFullYear();
    if(month < 10) {
    	month = '0' + month;
    }
    if(day < 10){
    	day = '0' + day;
    }
    var value =  year + '/' + month+ '/' + day;
    
    var result = false;
    for (var i=0; i<invalidDatesArray.length; i++) {
    	if (value == invalidDatesArray[i]) {
    		result=true;
    	}
    }
    	
    return [result,''];
};

function rtnAlphaNumeric(evt) {
	evt = evt || window.event;
	var charCode = evt.which || evt.keyCode;
	var charStr = String.fromCharCode(charCode);
	if (/[a-z0-9]/i.test(charStr)) {
		return true;
	}
	return false;
};

function rtnNumeric(evt) {
	evt = evt || window.event;
	var charCode = evt.which || evt.keyCode;
	var charStr = String.fromCharCode(charCode);
	if (/[0-9]/i.test(charStr)) {
		return true;
	}
	return false;
};

function rtnPhoneNo(evt) {
	evt = evt || window.event;
	var charCode = evt.which || evt.keyCode;
	var charStr = String.fromCharCode(charCode);
	/*var currStr = evt.target.value;
	if (charStr == '+' && currStr.includes('+')) return false;
	if (charStr == '+' && currStr.length > 0) {
		evt.target.value = '+' + evt.target.value;
		return false;
	}*/
	if (/[0-9]/i.test(charStr)) {
		return true;
	}
	return false;
};

function rtnUpperCase(evt) {
	evt = evt || window.event;
	evt.target.value = evt.target.value.toUpperCase();
};

function overridePrimeFacesDataTableScroll() {
	if (window.PrimeFaces && PrimeFaces.widget && PrimeFaces.widget.DataTable) {
		PrimeFaces.widget.DataTable.prototype.setupScrolling = function() {
			this.scrollHeader = $(this.jqId + " .ui-datatable-scrollable-header");
			this.scrollBody = $(this.jqId + " .ui-datatable-scrollable-body");
			this.scrollFooter = $(this.jqId + " .ui-datatable-scrollable-footer");
			this.scrollStateHolder = $(this.jqId + "_scrollState");
			var a = this;
			this.restoreScrollState();
			if (this.cfg.liveScroll) {
				this.scrollOffset = this.cfg.scrollStep;
				this.shouldLiveScroll = true;
				this._liveScrollLoading = false;
			}
			this.scrollHeader.scroll(function() {
				a.scrollBody.scrollLeft(a.scrollHeader.scrollLeft());
				a.scrollFooter.scrollLeft(a.scrollHeader.scrollLeft());
			});
			this.scrollBody.scroll(function() {
				a.scrollHeader.scrollLeft(a.scrollBody.scrollLeft());
				a.scrollFooter.scrollLeft(a.scrollBody.scrollLeft());
				if (a.shouldLiveScroll && !a._liveScrollLoading) {
					var scrollTop = Math.ceil(this.scrollTop),
						scrollHeight = this.scrollHeight,
						clientHeight = this.clientHeight;
					if (scrollTop + clientHeight >= scrollHeight - 1) {
						a._liveScrollLoading = true;
						a.loadLiveRows();
					}
				}
				a.saveScrollState();
			});
			if (a.isEmpty()) {
				a.alignEmptyMessage();
			}
		};

		var origLoadLiveRows = PrimeFaces.widget.DataTable.prototype.loadLiveRows;
		PrimeFaces.widget.DataTable.prototype.loadLiveRows = function() {
			var a = this;
			var origOnSuccess = null;
			var result = origLoadLiveRows.apply(this, arguments);
			setTimeout(function() { a._liveScrollLoading = false; }, 500);
			return result;
		};

		return true;
	}
	return false;
}

if (!overridePrimeFacesDataTableScroll()) {
	var _pfScrollPollCount = 0;
	var _pfScrollPollTimer = setInterval(function() {
		_pfScrollPollCount++;
		if (overridePrimeFacesDataTableScroll() || _pfScrollPollCount > 50) {
			clearInterval(_pfScrollPollTimer);
		}
	}, 200);
}

/* Global Android and Desktop Enter Key Handler for Filter/Search inputs */
(function() {
	var lastEnterTime = 0;

	window.setEnterKeyHint = function() {
		if (typeof document.querySelectorAll === 'function') {
			var inputs = document.querySelectorAll('input[type="text"], input[type="search"], input:not([type])');
			for (var i = 0; i < inputs.length; i++) {
				if (!inputs[i].getAttribute('enterkeyhint')) {
					inputs[i].setAttribute('enterkeyhint', 'search');
				}
			}
		}
	};

	$(document).ready(function() {
		window.setEnterKeyHint();
	});

	$(document).on('focus.setEnterHint', 'input[type="text"], input[type="search"], input:not([type])', function() {
		if (!this.getAttribute('enterkeyhint')) {
			this.setAttribute('enterkeyhint', 'search');
		}
	});

	function getDataTableWidget($dt) {
		if (!$dt || !$dt.length) return null;
		var dtId = $dt.attr('id');
		if (!dtId) return null;

		if (window.PrimeFaces) {
			if (typeof PrimeFaces.getWidgetByLocId === 'function') {
				var w = PrimeFaces.getWidgetByLocId(dtId);
				if (w && typeof w.filter === 'function') return w;
			}
			if (PrimeFaces.widgets) {
				for (var wVar in PrimeFaces.widgets) {
					var widget = PrimeFaces.widgets[wVar];
					if (widget && widget.id === dtId && typeof widget.filter === 'function') {
						return widget;
					}
				}
			}
		}
		var jqWidget = $dt.data('widget');
		if (jqWidget && typeof jqWidget.filter === 'function') return jqWidget;

		if (window[dtId] && typeof window[dtId].filter === 'function') return window[dtId];

		return null;
	}

	function handleGlobalEnter(e) {
		var isEnter = (e.key === 'Enter' || e.keyCode === 13 || e.which === 13);
		if (!isEnter) return;

		var target = e.target;
		if (!target || target.tagName !== 'INPUT') return;

		var inputType = (target.type || 'text').toLowerCase();
		if (inputType === 'submit' || inputType === 'button' || inputType === 'checkbox' || inputType === 'radio' || inputType === 'file' || inputType === 'image') {
			return;
		}

		var now = new Date().getTime();
		if (now - lastEnterTime < 300) {
			e.preventDefault();
			return false;
		}

		var $input = $(target);
		var $dt = $input.closest('.ui-datatable');

		if ($input.hasClass('ui-column-filter') || $input.closest('th, td.ui-filter-column, .ui-datatable-header').length > 0 || $dt.length > 0) {
			if ($dt.length) {
				var widget = getDataTableWidget($dt);
				if (widget && typeof widget.filter === 'function') {
					lastEnterTime = now;
					e.preventDefault();
					e.stopPropagation();
					widget.filter();
					return false;
				}
			}
			if ($input.hasClass('ui-column-filter')) {
				lastEnterTime = now;
				e.preventDefault();
				e.stopPropagation();
				$input.trigger('change');
				return false;
			}
		}

		var $container = $input.closest('form, .ui-dialog, .ui-panel, .ui-widget-content, body');
		if ($container.length) {
			var $searchBtn = $container.find('.ui-button, button, input[type="button"], input[type="submit"]').filter(function() {
				var $btn = $(this);
				if (!$btn.is(':visible') || $btn.is(':disabled')) return false;
				var txt = ($.trim($btn.text()) || $btn.val() || $btn.attr('id') || '').toLowerCase();
				var icon = ($btn.find('.ui-icon').attr('class') || '').toLowerCase();
				return txt.indexOf('search') !== -1 || txt.indexOf('filter') !== -1 || icon.indexOf('search') !== -1;
			}).first();

			if ($searchBtn.length) {
				lastEnterTime = now;
				e.preventDefault();
				e.stopPropagation();
				$searchBtn.click();
				return false;
			}
		}
	}

	$(document).on('keydown.androidSearch keyup.androidSearch', 'input[type="text"], input[type="search"], input:not([type])', function(e) {
		var isEnter = (e.key === 'Enter' || e.keyCode === 13 || e.which === 13);
		if (isEnter) {
			return handleGlobalEnter(e);
		}
	});
})();

(function() {
	var touchMoved = false;
	$(document).on('touchstart.awsTouch', '.ui-selectonemenu-item, .ui-selectcheckboxmenu-item, .ui-autocomplete-item', function() {
		touchMoved = false;
	}).on('touchmove.awsTouch', '.ui-selectonemenu-item, .ui-selectcheckboxmenu-item, .ui-autocomplete-item', function() {
		touchMoved = true;
	}).on('touchend.awsTouch', '.ui-selectonemenu-item:not(.ui-state-disabled), .ui-selectcheckboxmenu-item:not(.ui-state-disabled), .ui-autocomplete-item:not(.ui-state-disabled)', function(e) {
		if (!touchMoved) {
			e.preventDefault();
			$(this).trigger('click');
		}
	});
})();

(function() {
	var lastTouchTime = 0;

	$(document).on('touchend.awsMenubar click.awsMenubar', '.ui-menubar a.ui-menuitem-link', function(e) {
		var $link = $(this);
		var href = $link.attr('href');
		var onclickAttr = $link.attr('onclick');
		var $parentLi = $link.parent('.ui-menuitem');
		var $childUl = $parentLi.children('ul.ui-menu-child');
		var $menubar = $link.closest('.ui-menubar');
		var now = new Date().getTime();

		if ($menubar.length) {
			var mbId = $menubar.attr('id');
			if (mbId) {
				$menubar.find('*').data('primefaces-menubar', mbId);
			}
		}

		if (e.type === 'touchend') {
			lastTouchTime = now;
		} else if (e.type === 'click' && (now - lastTouchTime < 500)) {
			e.preventDefault();
			e.stopPropagation();
			return false;
		}

		if ($childUl.length > 0) {
			e.preventDefault();
			e.stopPropagation();

			if ($childUl.is(':visible')) {
				$parentLi.removeClass('ui-menuitem-active');
				$childUl.hide();
			} else {
				$parentLi.siblings().removeClass('ui-menuitem-active').find('ul.ui-menu-child').hide();
				$parentLi.addClass('ui-menuitem-active');
				$link.addClass('ui-state-hover');

				var zIndex = (window.PrimeFaces && PrimeFaces.zindex) ? ++PrimeFaces.zindex : 1000;
				if ($parentLi.parent().hasClass('ui-menu-child')) {
					$childUl.css({
						'left': $parentLi.outerWidth() + 'px',
						'top': '0px',
						'z-index': zIndex
					});
				} else {
					$childUl.css({
						'left': '0px',
						'top': $parentLi.outerHeight() + 'px',
						'z-index': zIndex
					});
				}
				$childUl.show();
			}
			return false;
		}

		if (e.type === 'touchend') {
			e.preventDefault();
			e.stopPropagation();

			if (onclickAttr) {
				$link[0].click();
			} else if (href && href !== '#' && href.indexOf('javascript:') !== 0) {
				window.location.href = href;
			} else {
				$link[0].click();
			}
			return false;
		}
	});
})();




