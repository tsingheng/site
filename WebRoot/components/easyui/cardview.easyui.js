﻿/**
 * jQuery EasyUI 1.2.5
 * 
 * Licensed under the GPL terms
 * To use it on other terms please contact us
 *
 * Copyright(c) 2009-2011 stworthy [ stworthy@gmail.com ] 
 * 
 */
(function($) {
	function getObjectIndex(rows, o) {
		for ( var i = 0, count = rows.length; i < count; i++) {
			if (rows[i] == o) {
				return i;
			}
		}
		return -1;
	};
	function unSelectedRowsById(rows, o, id) {
		if (typeof o == "string") {
			for ( var i = 0, count = rows.length; i < count; i++) {
				if (rows[i][o] == id) {
					rows.splice(i, 1);
					return;
				}
			}
		} else {
			var index = getObjectIndex(rows, o);
			if (index != -1) {
				rows.splice(index, 1);
			}
		}
	};
	function setSize(jq, param) {
		var opts = $.data(jq, "cardview").options;
		var panel = $.data(jq, "cardview").panel;
		if (param) {
			if (param.width) {
				opts.width = param.width;
			}
			if (param.height) {
				opts.height = param.height;
			}
		}
		if (opts.fit == true) {
			var p = panel.panel("panel").parent();
			opts.width = p.width();
			opts.height = p.height();
		}
		panel.panel("resize", {
			width : opts.width,
			height : opts.height
		});
	};
	function fitGridSize(jq) {
		var opts = $.data(jq, "cardview").options;
		var dc = $.data(jq, "cardview").dc;
		var panel = $.data(jq, "cardview").panel;
		var width = panel.width();
		var height = panel.height()-2;
		var gridView = dc.view;
		var gridView2 = dc.view2;
		gridView.width(width);
		gridView2.width(width);
		gridView2.children(
				"div.datagrid-body")
				.width(gridView2.width());
		if (opts.height != "auto") {
			var fixedHeight = height
					- panel.children("div.datagrid-pager").outerHeight(true);
			gridView2.children("div.datagrid-body").height(fixedHeight);
		}
		gridView.height(gridView2.height());
		gridView2.css("left", 0);
	};
	function setMsgSize(jq) {
		var panel = $(jq).cardview("getPanel");
		var mask = panel.children("div.datagrid-mask");
		if (mask.length) {
			mask.css( {
				width : panel.width(),
				height : panel.height()
			});
			var msg = panel.children("div.datagrid-mask-msg");
			msg.css( {
				left : (panel.width() - msg.outerWidth()) / 2,
				top : (panel.height() - msg.outerHeight()) / 2
			});
		}
	};
	function wrapGrid(jq) {
		var wrap = $(
				"<div class=\"datagrid-wrap\">"
						+ "<div class=\"datagrid-view\">"
						+ "<div class=\"datagrid-view1\">"
						+ "<div class=\"datagrid-header\">"
						+ "<div class=\"datagrid-header-inner\"></div>"
						+ "</div>" + "<div class=\"datagrid-body\">"
						+ "<div class=\"datagrid-body-inner\"></div>"
						+ "</div>" + "<div class=\"datagrid-footer\">"
						+ "<div class=\"datagrid-footer-inner\"></div>"
						+ "</div>" + "</div>"
						+ "<div class=\"datagrid-view2\">"
						+ "<div class=\"datagrid-header\">"
						+ "<div class=\"datagrid-header-inner\"></div>"
						+ "</div>" + "<div class=\"datagrid-body\"></div>"
						+ "<div class=\"datagrid-footer\">"
						+ "<div class=\"datagrid-footer-inner\"></div>"
						+ "</div>" + "</div>"
						+ "<div class=\"datagrid-resize-proxy\"></div>"
						+ "</div>" + "</div>").insertAfter(jq);
		wrap.panel( {
			doSize : false
		});
		wrap.panel("panel").addClass("cardview").bind("_resize",
				function(e, param) {
					var opts = $.data(jq, "cardview").options;
					if (opts.fit == true || param) {
						setSize(jq);
					}
					return false;
				});
		$(jq).hide().appendTo(wrap.children("div.datagrid-view"));
		var gridView = wrap.children("div.datagrid-view");
		var gridView2 = gridView.children("div.datagrid-view2");
		return {
			panel : wrap,
			dc : {
				view : gridView,
				view2 : gridView2,
				body2 : gridView2.children("div.datagrid-body")
			}
		};
	};
	function init(jq) {
		var opts = $.data(jq, "cardview").options;
		var dc = $.data(jq, "cardview").dc;
		var panel = $.data(jq, "cardview").panel;
		panel.panel($.extend( {}, opts, {
			doSize : false,
			onResize : function(width, height) {
				setMsgSize(jq);
				setTimeout(function() {
					if ($.data(jq, "cardview")) {
						fitGridSize(jq);
						opts.onResize.call(panel, width, height);
					}
				}, 0);
			},
			onExpand : function() {
				fitGridSize(jq);
				opts.onExpand.call(panel);
			}
		}));
		var gridView2 = dc.view2;
		if (opts.toolbar) {
			if (typeof opts.toolbar == "string") {
				$(opts.toolbar).addClass("datagrid-toolbar").prependTo(panel);
				$(opts.toolbar).show();
			} else {
				$("div.datagrid-toolbar", panel).remove();
				var tb = $("<div class=\"datagrid-toolbar\"></div>").prependTo(
						panel);
				for ( var i = 0; i < opts.toolbar.length; i++) {
					var btn = opts.toolbar[i];
					if (btn == "-") {
						$("<div class=\"datagrid-btn-separator\"></div>")
								.appendTo(tb);
					} else {
						var tool = $("<a href=\"javascript:void(0)\"></a>");
						tool[0].onclick = eval(btn.handler || function() {
						});
						tool.css("float", "left").appendTo(tb).linkbutton(
								$.extend( {}, btn, {
									plain : true
								}));
					}
				}
			}
		} else {
			$("div.datagrid-toolbar", panel).remove();
		}
		$("div.datagrid-pager", panel).remove();
		if (opts.pagination) {
			var pager = $("<div class=\"datagrid-pager\"></div>").appendTo(panel);
			pager.pagination( {
				pageNumber : opts.pageNumber,
				pageSize : opts.pageSize,
				pageList : opts.pageList,
				onSelectPage : function(pageNumber, pageSize) {
					opts.pageNumber = pageNumber;
					opts.pageSize = pageSize;
					request(jq);
				}
			});
			opts.pageSize = pager.pagination("options").pageSize;
		}
	};
	function resetGridEvent(jq) {
		var opts = $.data(jq, "cardview").options;
		var data = $.data(jq, "cardview").data;
		var td = opts.finder.getTd(jq, "", "allbody");
		td.unbind(".cardview").bind("mouseenter.cardview", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			opts.finder.getTd(jq, rowIndex).addClass("datagrid-row-over");
		}).bind("mouseleave.cardview", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			opts.finder.getTd(jq, rowIndex).removeClass("datagrid-row-over");
		}).bind("click.cardview", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			if (opts.singleSelect == true) {
				clearSelections(jq);
				selectRow(jq, rowIndex);
			} else {
				if ($(this).hasClass("datagrid-row-selected")) {
					unselectRow(jq, rowIndex);
				} else {
					selectRow(jq, rowIndex);
				}
			}
			if (opts.onClickRow) {
				opts.onClickRow.call(jq, rowIndex, data.rows[rowIndex]);
			}
		}).bind("dblclick.cardview", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			if (opts.onDblClickRow) {
				opts.onDblClickRow.call(jq, rowIndex, data.rows[rowIndex]);
			}
		}).bind("contextmenu.cardview", function(e) {
			var rowIndex = $(this).attr("datagrid-row-index");
			if (opts.onRowContextMenu) {
				opts.onRowContextMenu.call(jq, e, rowIndex, data.rows[rowIndex]);
			}
		});
	};
	function setProperties(jq) {
		var panel = $.data(jq, "cardview").panel;
		var opts = $.data(jq, "cardview").options;
		var dc = $.data(jq, "cardview").dc;
		dc.body2.unbind(".cardview").bind(
				"scroll.cardview",
				function() {
				});
	};
	function renderGrid(jq, data) {
		var opts = $.data(jq, "cardview").options;
		var dc = $.data(jq, "cardview").dc;
		var panel = $.data(jq, "cardview").panel;
		var selectedRows = $.data(jq, "cardview").selectedRows;
		data = opts.loadFilter.call(jq, data);
		var rows = data.rows;
		$.data(jq, "cardview").data = data;
		if (opts.view.onBeforeRender) {
			opts.view.onBeforeRender.call(opts.view, jq, rows);
		}
		opts.view.render.call(opts.view, jq, dc.body2, false);
		if (opts.view.onAfterRender) {
			opts.view.onAfterRender.call(opts.view, jq);
		}
		opts.onLoadSuccess.call(jq, data);
		var pager = panel.children("div.datagrid-pager");
		if (pager.length) {
			if (pager.pagination("options").total != data.total) {
				pager.pagination( {
					total : data.total
				});
			}
		}
		resetGridEvent(jq);
		dc.body2.triggerHandler("scroll");
		if (opts.idField) {
			for ( var i = 0; i < rows.length; i++) {
				if (isSelected(rows[i])) {
					selectRecord(jq, rows[i][opts.idField]);
				}
			}
		}
		function isSelected(row) {
			for ( var i = 0; i < selectedRows.length; i++) {
				if (selectedRows[i][opts.idField] == row[opts.idField]) {
					selectedRows[i] = row;
					return true;
				}
			}
			return false;
		};
	};
	function getRowIndex(jq, row) {
		var opts = $.data(jq, "cardview").options;
		var rows = $.data(jq, "cardview").data.rows;
		if (typeof row == "object") {
			return getObjectIndex(rows, row);
		} else {
			for ( var i = 0; i < rows.length; i++) {
				if (rows[i][opts.idField] == row) {
					return i;
				}
			}
			return -1;
		}
	};
	function getSelected(jq) {
		var opts = $.data(jq, "cardview").options;
		var data = $.data(jq, "cardview").data;
		if (opts.idField) {
			return $.data(jq, "cardview").selectedRows;
		} else {
			var rowIndexs = [];
			opts.finder.getTd(jq, "", "selected", 2).each(function() {
				var rowIndex = parseInt($(this).attr("datagrid-row-index"));
				rowIndexs.push(data.rows[rowIndex]);
			});
			return rowIndexs;
		}
	};
	function clearSelections(jq) {
		unselectAll(jq);
		var selectedRows = $.data(jq, "cardview").selectedRows;
		selectedRows.splice(0, selectedRows.length);
	};
	function selectAll(jq) {
		var opts = $.data(jq, "cardview").options;
		var rows = $.data(jq, "cardview").data.rows;
		var selectedRows = $.data(jq, "cardview").selectedRows;
		var td = opts.finder.getTd(jq, "", "allbody").addClass(
				"datagrid-row-selected");
		for ( var rowIndex = 0; rowIndex < rows.length; rowIndex++) {
			if (opts.idField) {
				(function() {
					var row = rows[rowIndex];
					for ( var i = 0; i < selectedRows.length; i++) {
						if (selectedRows[i][opts.idField] == row[opts.idField]) {
							return;
						}
					}
					selectedRows.push(row);
				})();
			}
		}
		opts.onSelectAll.call(jq, rows);
	};
	function unselectAll(jq) {
		var opts = $.data(jq, "cardview").options;
		var data = $.data(jq, "cardview").data;
		var selectedRows = $.data(jq, "cardview").selectedRows;
		var td = opts.finder.getTd(jq, "", "selected").removeClass("datagrid-row-selected");
		if (opts.idField) {
			for ( var rowIndex = 0; rowIndex < data.rows.length; rowIndex++) {
				unSelectedRowsById(selectedRows, opts.idField, data.rows[rowIndex][opts.idField]);
			}
		}
		opts.onUnselectAll.call(jq, data.rows);
	};
	function selectRow(jq, rowIndex) {
		var dc = $.data(jq, "cardview").dc;
		var opts = $.data(jq, "cardview").options;
		var data = $.data(jq, "cardview").data;
		var selectedRows = $.data(jq, "cardview").selectedRows;
		if (rowIndex < 0 || rowIndex >= data.rows.length) {
			return;
		}
		if (opts.singleSelect == true) {
			clearSelections(jq);
		}
		var td = opts.finder.getTd(jq, rowIndex);
		if (!td.hasClass("datagrid-row-selected")) {
			td.addClass("datagrid-row-selected");
			if (opts.idField) {
				var row = data.rows[rowIndex];
				(function() {
					for ( var i = 0; i < selectedRows.length; i++) {
						if (selectedRows[i][opts.idField] == row[opts.idField]) {
							return;
						}
					}
					selectedRows.push(row);
				})();
			}
		}
		opts.onSelect.call(jq, rowIndex, data.rows[rowIndex]);
		var gridBody = dc.body2;
		var top = td.position().top;
		if (top <= 0) {
			gridBody.scrollTop(gridBody.scrollTop() + top);
		} else {
			if (top + td.outerHeight() > gridBody.height() - 18) {
				gridBody.scrollTop(gridBody.scrollTop() + top + td.outerHeight()
						- gridBody.height() + 18);
			}
		}
	};
	function selectRecord(jq, id) {
		var opts = $.data(jq, "cardview").options;
		var data = $.data(jq, "cardview").data;
		if (opts.idField) {
			var index = -1;
			for ( var i = 0; i < data.rows.length; i++) {
				if (data.rows[i][opts.idField] == id) {
					index = i;
					break;
				}
			}
			if (index >= 0) {
				selectRow(jq, index);
			}
		}
	};
	function unselectRow(jq, rowIndex) {
		var opts = $.data(jq, "cardview").options;
		var dc = $.data(jq, "cardview").dc;
		var data = $.data(jq, "cardview").data;
		var selectedRows = $.data(jq, "cardview").selectedRows;
		if (rowIndex < 0 || rowIndex >= data.rows.length) {
			return;
		}
		var td = opts.finder.getTd(jq, rowIndex);
		td.removeClass("datagrid-row-selected");
		var row = data.rows[rowIndex];
		if (opts.idField) {
			unSelectedRowsById(selectedRows, opts.idField, row[opts.idField]);
		}
		opts.onUnselect.call(jq, rowIndex, row);
	};
	function request(jq, param) {
		var opts = $.data(jq, "cardview").options;
		if (param) {
			opts.queryParams = param;
		}
		if (!opts.url) {
			return;
		}
		var param = $.extend( {}, opts.queryParams);
		if (opts.pagination) {
			$.extend(param, {
				page : opts.pageNumber,
				rows : opts.pageSize
			});
		}
		if (opts.sortName) {
			$.extend(param, {
				sort : opts.sortName,
				order : opts.sortOrder
			});
		}
		if (opts.onBeforeLoad.call(jq, param) == false) {
			return;
		}
		$(jq).cardview("loading");
		setTimeout(function() {
			doRequest();
		}, 0);
		function doRequest() {
			$.ajax( {
				type : opts.method,
				url : opts.url,
				data : param,
				dataType : "json",
				success : function(data) {
					setTimeout(function() {
						$(jq).cardview("loaded");
					}, 0);
					renderGrid(jq, data);
				},
				error : function() {
					setTimeout(function() {
						$(jq).cardview("loaded");
					}, 0);
					if (opts.onLoadError) {
						opts.onLoadError.apply(jq, arguments);
					}
				}
			});
		};
	};
	$.fn.cardview = function(options, param) {
		if (typeof options == "string") {
			return $.fn.cardview.methods[options](this, param);
		}
		options = options || {};
		return this.each(function() {
			var state = $.data(this, "cardview");
			var opts;
			if (state) {
				opts = $.extend(state.options, options);
				state.options = opts;
			} else {
				opts = $.extend( {}, $.extend( {}, $.fn.cardview.defaults, {
					queryParams : {}
				}), $.fn.cardview.parseOptions(this), options);
				$(this).css("width", "").css("height", "");
				var gridWrap = wrapGrid(this);
				$.data(this, "cardview", {
					options : opts,
					panel : gridWrap.panel,
					dc : gridWrap.dc,
					selectedRows : [],
					data : {
						total : 0,
						rows : []
					}
				});
			}
			init(this);
			setSize(this);
			if (opts.url) {
				request(this);
			}
			setProperties(this);
		});
	};
	var editors = {
		text : {
			init : function(container, options) {
				var editor = $("<input type=\"text\" class=\"datagrid-editable-input\">").appendTo(container);
				return editor;
			},
			getValue : function(jq) {
				return $(jq).val();
			},
			setValue : function(jq, value) {
				$(jq).val(value);
			},
			resize : function(jq, width) {
				var editor = $(jq);
				if ($.boxModel == true) {
					editor.width(width - (editor.outerWidth() - editor.width()));
				} else {
					editor.width(width);
				}
			}
		},
		textarea : {
			init : function(container, options) {
				var editor = $("<textarea class=\"datagrid-editable-input\"></textarea>").appendTo(container);
				return editor;
			},
			getValue : function(jq) {
				return $(jq).val();
			},
			setValue : function(jq, value) {
				$(jq).val(value);
			},
			resize : function(jq, width) {
				var editor = $(jq);
				if ($.boxModel == true) {
					editor.width(width - (editor.outerWidth() - editor.width()));
				} else {
					editor.width(width);
				}
			}
		},
		checkbox : {
			init : function(container, options) {
				var editor = $("<input type=\"checkbox\">").appendTo(container);
				editor.val(options.on);
				editor.attr("offval", options.off);
				return editor;
			},
			getValue : function(jq) {
				if ($(jq).is(":checked")) {
					return $(jq).val();
				} else {
					return $(jq).attr("offval");
				}
			},
			setValue : function(jq, value) {
				var checked = false;
				if ($(jq).val() == value) {
					checked = true;
				}
				$.fn.prop ? $(jq).prop("checked", checked) : $(jq).attr(
						"checked", checked);
			}
		},
		numberbox : {
			init : function(container, options) {
				var editor = $("<input type=\"text\" class=\"datagrid-editable-input\">").appendTo(container);
				editor.numberbox(options);
				return editor;
			},
			destroy : function(jq) {
				$(jq).numberbox("destroy");
			},
			getValue : function(jq) {
				return $(jq).numberbox("getValue");
			},
			setValue : function(jq, value) {
				$(jq).numberbox("setValue", value);
			},
			resize : function(jq, width) {
				var editor = $(jq);
				if ($.boxModel == true) {
					editor.width(width - (editor.outerWidth() - editor.width()));
				} else {
					editor.width(width);
				}
			}
		},
		validatebox : {
			init : function(container, options) {
				var editor = $(
						"<input type=\"text\" class=\"datagrid-editable-input\">")
						.appendTo(container);
				editor.validatebox(options);
				return editor;
			},
			destroy : function(jq) {
				$(jq).validatebox("destroy");
			},
			getValue : function(jq) {
				return $(jq).val();
			},
			setValue : function(jq, value) {
				$(jq).val(value);
			},
			resize : function(jq, width) {
				var editor = $(jq);
				if ($.boxModel == true) {
					editor.width(width - (editor.outerWidth() - editor.width()));
				} else {
					editor.width(width);
				}
			}
		},
		datebox : {
			init : function(container, options) {
				var editor = $("<input type=\"text\">").appendTo(container);
				editor.datebox(options);
				return editor;
			},
			destroy : function(jq) {
				$(jq).datebox("destroy");
			},
			getValue : function(jq) {
				return $(jq).datebox("getValue");
			},
			setValue : function(jq, value) {
				$(jq).datebox("setValue", value);
			},
			resize : function(jq, width) {
				$(jq).datebox("resize", width);
			}
		},
		combobox : {
			init : function(container, options) {
				var editor = $("<input type=\"text\">").appendTo(container);
				editor.combobox(options || {});
				return editor;
			},
			destroy : function(jq) {
				$(jq).combobox("destroy");
			},
			getValue : function(jq) {
				return $(jq).combobox("getValue");
			},
			setValue : function(jq, value) {
				$(jq).combobox("setValue", value);
			},
			resize : function(jq, width) {
				$(jq).combobox("resize", width);
			}
		},
		combotree : {
			init : function(container, options) {
				var editor = $("<input type=\"text\">").appendTo(container);
				editor.combotree(options);
				return editor;
			},
			destroy : function(jq) {
				$(jq).combotree("destroy");
			},
			getValue : function(jq) {
				return $(jq).combotree("getValue");
			},
			setValue : function(jq, value) {
				$(jq).combotree("setValue", value);
			},
			resize : function(jq, width) {
				$(jq).combotree("resize", width);
			}
		}
	};
	$.fn.cardview.methods = {
		options : function(jq) {
			var gridOpts = $.data(jq[0], "cardview").options;
			var panelOpts = $.data(jq[0], "cardview").panel.panel("options");
			var opts = $.extend(gridOpts, {
				width : panelOpts.width,
				height : panelOpts.height,
				closed : panelOpts.closed,
				collapsed : panelOpts.collapsed,
				minimized : panelOpts.minimized,
				maximized : panelOpts.maximized
			});
			var pager = jq.cardview("getPager");
			if (pager.length) {
				var pagerOpts = pager.pagination("options");
				$.extend(opts, {
					pageNumber : pagerOpts.pageNumber,
					pageSize : pagerOpts.pageSize
				});
			}
			return opts;
		},
		getPanel : function(jq) {
			return $.data(jq[0], "cardview").panel;
		},
		getPager : function(jq) {
			return $.data(jq[0], "cardview").panel.find("div.datagrid-pager");
		},
		resize : function(jq, param) {
			return jq.each(function() {
				setSize(this, param);
			});
		},
		load : function(jq, param) {
			return jq.each(function() {
				var opts = $(this).cardview("options");
				opts.pageNumber = 1;
				var pager = $(this).cardview("getPager");
				pager.pagination( {
					pageNumber : 1
				});
				request(this, param);
			});
		},
		reload : function(jq, param) {
			return jq.each(function() {
				request(this, param);
			});
		},
		loading : function(jq) {
			return jq.each(function() {
				var opts = $.data(this, "cardview").options;
				$(this).cardview("getPager").pagination("loading");
				if (opts.loadMsg) {
					var panel = $(this).cardview("getPanel");
					$("<div class=\"datagrid-mask\" style=\"display:block\"></div>").appendTo(panel);
					$("<div class=\"datagrid-mask-msg\" style=\"display:block\"></div>").html(opts.loadMsg).appendTo(panel);
					setMsgSize(this);
				}
			});
		},
		loaded : function(jq) {
			return jq.each(function() {
				$(this).cardview("getPager").pagination("loaded");
				var panel = $(this).cardview("getPanel");
				panel.children("div.datagrid-mask-msg").remove();
				panel.children("div.datagrid-mask").remove();
			});
		},
		loadData : function(jq, data) {
			return jq.each(function() {
				renderGrid(this, data);
			});
		},
		getData : function(jq) {
			return $.data(jq[0], "cardview").data;
		},
		getRows : function(jq) {
			return $.data(jq[0], "cardview").data.rows;
		},
		getRowIndex : function(jq, id) {
			return getRowIndex(jq[0], id);
		},
		getSelected : function(jq) {
			var rows = getSelected(jq[0]);
			return rows.length > 0 ? rows[0] : null;
		},
		getSelections : function(jq) {
			return getSelected(jq[0]);
		},
		clearSelections : function(jq) {
			return jq.each(function() {
				clearSelections(this);
			});
		},
		selectAll : function(jq) {
			return jq.each(function() {
				selectAll(this);
			});
		},
		unselectAll : function(jq) {
			return jq.each(function() {
				unselectAll(this);
			});
		},
		selectRow : function(jq, index) {
			return jq.each(function() {
				selectRow(this, index);
			});
		},
		selectRecord : function(jq, id) {
			return jq.each(function() {
				selectRecord(this, id);
			});
		},
		unselectRow : function(jq, index) {
			return jq.each(function() {
				unselectRow(this, index);
			});
		}
	};
	$.fn.cardview.parseOptions = function(jq) {
		var t = $(jq);
		return $.extend({},$.fn.panel.parseOptions(jq),{
			rownumbers : (t.attr("rownumbers") ? t
					.attr("rownumbers") == "true" : undefined),
			singleSelect : (t.attr("singleSelect") ? t
					.attr("singleSelect") == "true" : undefined),
			pagination : (t.attr("pagination") ? t
					.attr("pagination") == "true" : undefined),
			pageSize : (t.attr("pageSize") ? parseInt(t
					.attr("pageSize")) : undefined),
			pageNumber : (t.attr("pageNumber") ? parseInt(t
					.attr("pageNumber")) : undefined),
			pageList : (t.attr("pageList") ? eval(t
					.attr("pageList")) : undefined),
			cols : t.attr("cols"),
			scrollbarSize : (t.attr("scrollbarSize") ? parseInt(t
					.attr("scrollbarSize"))
					: undefined),
			loadMsg : (t.attr("loadMsg") != undefined ? t
					.attr("loadMsg") : undefined),
			idField : t.attr("idField"),
			toolbar : t.attr("toolbar"),
			url : t.attr("url"),
			rowStyler : (t.attr("rowStyler") ? eval(t
					.attr("rowStyler")) : undefined)
		});
	};
	var view = {
		render : function(jq, container, frozen) {
			var opts = $.data(jq, "cardview").options;
			var rows = $.data(jq, "cardview").data.rows;
			var html = [ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
			for ( var i = 0; i < rows.length; i++) {
				var style = opts.rowStyler ? opts.rowStyler.call(jq, i,rows[i]) : "";
				var style = style ? "style=\"" + style + "\"" : "";
				html.push("<tr datagrid-row-index=\"" + i + "\" "+ style + ">");
				html.push("<td>sssssss</td>")
				//html.push(this.renderRow.call(this, jq, fields, frozen, i,rows[i]));
				html.push("</tr>");
			}
			html.push("</tbody></table>");
			$(container).html(html.join(""));
		},
		renderRow : function(jq, fields, frozen, rowIndex, rowData) {
			var opts = $.data(jq, "datagrid").options;
			var cc = [];
			if (frozen && opts.rownumbers) {
				var rowNumber = rowIndex + 1;
				if (opts.pagination) {
					rowNumber += (opts.pageNumber - 1) * opts.pageSize;
				}
				cc.push("<td class=\"datagrid-td-rownumber\"><div class=\"datagrid-cell-rownumber\">" + rowNumber + "</div></td>");
			}
			for ( var i = 0; i < fields.length; i++) {
				var field = fields[i];
				var col = $(jq).datagrid("getColumnOption", field);
				if (col) {
					var style = col.styler ? (col.styler(rowData[field], rowData, rowIndex) || "") : "";
					var style = col.hidden ? "style=\"display:none;" + style + "\"" : (style ? "style=\"" + style + "\"" : "");
					cc.push("<td field=\"" + field + "\" " + style + ">");
					var style = "width:" + (col.boxWidth) + "px;";
					style += "text-align:" + (col.align || "left") + ";";
					style += opts.nowrap == false ? "white-space:normal;" : "";
					cc.push("<div style=\"" + style + "\" ");
					if (col.checkbox) {
						cc.push("class=\"datagrid-cell-check ");
					} else {
						cc.push("class=\"datagrid-cell ");
					}
					cc.push("\">");
					if (col.checkbox) {
						cc.push("<input type=\"checkbox\"/>");
					} else {
						if (col.formatter) {
							cc.push(col.formatter(rowData[field], rowData, rowIndex));
						} else {
							cc.push(rowData[field]);
						}
					}
					cc.push("</div>");
					cc.push("</td>");
				}
			}
			return cc.join("");
		},
		onBeforeRender : function(jq, rows) {},
		onAfterRender : function(jq) {}
	};
	$.fn.cardview.defaults = $.extend({},$.fn.panel.defaults,{
		toolbar : null,
		cols: 1,
		method : "post",
		idField : null,
		url : null,
		loadMsg : "Processing, please wait ...",
		singleSelect : false,
		pagination : false,
		pageNumber : 1,
		pageSize : 10,
		pageList : [ 10, 20, 30, 40, 50 ],
		queryParams : {},
		scrollbarSize : 18,
		rowStyler : function(rowIndex, rowData) {
		},
		loadFilter : function(data) {
			if (typeof data.length == "number"
					&& typeof data.splice == "function") {
				return {
					total : data.length,
					rows : data
				};
			} else {
				return data;
			}
		},
		finder : {
			getTd : function(jq, rowIndex, type, step) {
				type = type || "body";
				step = step || 0;
				var dc = $.data(jq, "cardview").dc;
				var opts = $.data(jq, "cardview").options;
				if (step == 0) {
					//var td1 = opts.finder.getTd(jq, rowIndex,type, 1);
					var td2 = opts.finder.getTd(jq, rowIndex,type, 2);
					return td2;
				} else {
					if (type == "body") {
						return (step == 1 ? dc.body1 : dc.body2)
								.find(">table>tbody>tr>td[datagrid-row-index=" + rowIndex + "]");
					} else {
						if (type == "footer") {
							return (step == 1 ? dc.footer1 : dc.footer2).find(">table>tbody>tr>td[datagrid-row-index=" + rowIndex + "]");
						} else {
							if (type == "selected") {
								return (step == 1 ? dc.body1 : dc.body2).find(">table>tbody>tr>td.datagrid-row-selected");
							} else {
								if (type == "last") {
									return (step == 1 ? dc.body1 : dc.body2).find(">table>tbody>tr>td:last[datagrid-row-index]");
								} else {
									if (type == "allbody") {
										return (step == 1 ? dc.body1 : dc.body2).find(">table>tbody>tr>td[datagrid-row-index]");
									} else {
										if (type == "allfooter") {
											return (step == 1 ? dc.footer1 : dc.footer2).find(">table>tbody>tr>td[datagrid-row-index]");
										}
									}
								}
							}
						}
					}
				}
			},
			getRow : function(jq, rowIndex) {
				return $.data(jq, "cardview").data.rows[rowIndex];
			}
		},
		view : view,
		onBeforeLoad : function(param) {},
		onLoadSuccess : function() {},
		onLoadError : function() {},
		onClickRow : function(rowIndex, rowData) {},
		onDblClickRow : function(rowIndex, rowData) {},
		onClickCell : function(rowIndex, field, value) {},
		onDblClickCell : function(rowIndex, field, value) {},
		onSelect : function(rowIndex, rowData) {},
		onUnselect : function(rowIndex, rowData) {},
		onSelectAll : function(rows) {},
		onUnselectAll : function(rows) {},
		onRowContextMenu : function(e, owIndex, rowData) {}
	});
})(jQuery);