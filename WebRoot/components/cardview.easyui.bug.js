/**
 * jQuery EasyUI 1.2.5
 * 
 * Licensed under the GPL terms
 * To use it on other terms please contact us
 *
 * Copyright(c) 2009-2011 stworthy [ stworthy@gmail.com ] 
 * xxx
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
		var opts = $.data(jq, "datagrid").options;
		var panel = $.data(jq, "datagrid").panel;
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
		var opts = $.data(jq, "datagrid").options;
		var dc = $.data(jq, "datagrid").dc;
		var panel = $.data(jq, "datagrid").panel;
		var width = panel.width();
		var height = panel.height();
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
		var panel = $(jq).datagrid("getPanel");
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
						+ "<div class=\"datagrid-view2\">"
							+ "<div class=\"datagrid-body\"></div>"
						+ "</div>"
						+ "<div class=\"datagrid-resize-proxy\"></div>"
					+ "</div>" 
          		+ "</div>")
            	.insertAfter(jq);
		wrap.panel( {
			doSize : false
		});
		wrap.panel("panel").addClass("datagrid").bind("_resize",
				function(e, param) {
					var opts = $.data(jq, "datagrid").options;
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
		var opts = $.data(jq, "datagrid").options;
		var dc = $.data(jq, "datagrid").dc;
		var panel = $.data(jq, "datagrid").panel;
		panel.panel($.extend( {}, opts, {
			id: "",
			doSize : false,
			onResize : function(width, height) {
				setMsgSize(jq);
				setTimeout(function() {
					if ($.data(jq, "datagrid")) {
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
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
		var tr = opts.finder.getTr(jq, "", "allbody");
		tr.unbind(".datagrid").bind("mouseenter.datagrid", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			opts.finder.getTr(jq, rowIndex).addClass("datagrid-row-over");
		}).bind("mouseleave.datagrid", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			opts.finder.getTr(jq, rowIndex).removeClass("datagrid-row-over");
		}).bind("click.datagrid", function() {
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
		}).bind("dblclick.datagrid", function() {
			var rowIndex = $(this).attr("datagrid-row-index");
			if (opts.onDblClickRow) {
				opts.onDblClickRow.call(jq, rowIndex, data.rows[rowIndex]);
			}
		}).bind("contextmenu.datagrid", function(e) {
			var rowIndex = $(this).attr("datagrid-row-index");
			if (opts.onRowContextMenu) {
				opts.onRowContextMenu.call(jq, e, rowIndex, data.rows[rowIndex]);
			}
		});
	};
	function setProperties(jq) {
		var panel = $.data(jq, "datagrid").panel;
		var opts = $.data(jq, "datagrid").options;
		var dc = $.data(jq, "datagrid").dc;
		dc.body2.unbind(".datagrid").bind(
				"scroll.datagrid",
				function() {
					dc.view2.children("div.datagrid-header").scrollLeft(
							$(this).scrollLeft());
					dc.view2.children("div.datagrid-footer").scrollLeft(
							$(this).scrollLeft());
				});
	};
	function renderGrid(jq, data) {
		var opts = $.data(jq, "datagrid").options;
		var dc = $.data(jq, "datagrid").dc;
		var panel = $.data(jq, "datagrid").panel;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		data = opts.loadFilter.call(jq, data);
		var rows = data.rows;
		$.data(jq, "datagrid").data = data;
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
		var opts = $.data(jq, "datagrid").options;
		var rows = $.data(jq, "datagrid").data.rows;
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
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
		if (opts.idField) {
			return $.data(jq, "datagrid").selectedRows;
		} else {
			var rowIndexs = [];
			opts.finder.getTr(jq, "", "selected", 2).each(function() {
				var rowIndex = parseInt($(this).attr("datagrid-row-index"));
				rowIndexs.push(data.rows[rowIndex]);
			});
			return rowIndexs;
		}
	};
	function clearSelections(jq) {
		unselectAll(jq);
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		selectedRows.splice(0, selectedRows.length);
	};
	function selectAll(jq) {
		var opts = $.data(jq, "datagrid").options;
		var rows = $.data(jq, "datagrid").data.rows;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		var tr = opts.finder.getTr(jq, "", "allbody").addClass(
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
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		var tr = opts.finder.getTr(jq, "", "selected").removeClass("datagrid-row-selected");	
      	if (opts.idField) {
			for ( var rowIndex = 0; rowIndex < data.rows.length; rowIndex++) {
				unSelectedRowsById(selectedRows, opts.idField, data.rows[rowIndex][opts.idField]);
			}
		}
		opts.onUnselectAll.call(jq, data.rows);
	};
	function selectRow(jq, rowIndex) {
		var dc = $.data(jq, "datagrid").dc;
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		if (rowIndex < 0 || rowIndex >= data.rows.length) {
			return;
		}
		if (opts.singleSelect == true) {
			clearSelections(jq);
		}
		var tr = opts.finder.getTr(jq, rowIndex);
		if (!tr.hasClass("datagrid-row-selected")) {
			tr.addClass("datagrid-row-selected");
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
		var height = dc.view2.children("div.datagrid-header").outerHeight();
		var gridBody = dc.body2;
		var top = tr.position().top - height;
		if (top <= 0) {
			gridBody.scrollTop(gridBody.scrollTop() + top);
		} else {
			if (top + tr.outerHeight() > gridBody.height() - 18) {
				gridBody.scrollTop(gridBody.scrollTop() + top + tr.outerHeight()
						- gridBody.height() + 18);
			}
		}
	};
	function selectRecord(jq, id) {
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
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
		var opts = $.data(jq, "datagrid").options;
		var dc = $.data(jq, "datagrid").dc;
		var data = $.data(jq, "datagrid").data;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		if (rowIndex < 0 || rowIndex >= data.rows.length) {
			return;
		}
		var tr = opts.finder.getTr(jq, rowIndex);
		tr.removeClass("datagrid-row-selected");
		var row = data.rows[rowIndex];
		if (opts.idField) {
			unSelectedRowsById(selectedRows, opts.idField, row[opts.idField]);
		}
		opts.onUnselect.call(jq, rowIndex, row);
	};
	function deleteRow(jq, rowIndex) {
		var opts = $.data(jq, "datagrid").options;
		var data = $.data(jq, "datagrid").data;
		var insertedRows = $.data(jq, "datagrid").insertedRows;
		var deletedRows = $.data(jq, "datagrid").deletedRows;
		var selectedRows = $.data(jq, "datagrid").selectedRows;
		$(jq).datagrid("cancelEdit", rowIndex);
		var row = data.rows[rowIndex];
		if (getObjectIndex(insertedRows, row) >= 0) {
			unSelectedRowsById(insertedRows, row);
		} else {
			deletedRows.push(row);
		}
		unSelectedRowsById(selectedRows, opts.idField, data.rows[rowIndex][opts.idField]);
		opts.view.deleteRow.call(opts.view, jq, rowIndex);
	};
	function insertRow(jq, param) {
		var view = $.data(jq, "datagrid").options.view;
		var insertedRows = $.data(jq, "datagrid").insertedRows;
		view.insertRow.call(view, jq, param.index, param.row);
		resetGridEvent(jq);
		insertedRows.push(param.row);
	};
	function appendRow(jq, row) {
		var view = $.data(jq, "datagrid").options.view;
		var insertedRows = $.data(jq, "datagrid").insertedRows;
		view.insertRow.call(view, jq, null, row);
		resetGridEvent(jq);
		insertedRows.push(row);
	};
	function resetOperation(jq) {
		var data = $.data(jq, "datagrid").data;
		var rows = data.rows;
		var originalRows = [];
		for ( var i = 0; i < rows.length; i++) {
			originalRows.push($.extend( {}, rows[i]));
		}
		$.data(jq, "datagrid").originalRows = originalRows;
		$.data(jq, "datagrid").updatedRows = [];
		$.data(jq, "datagrid").insertedRows = [];
		$.data(jq, "datagrid").deletedRows = [];
	};
	function request(jq, param) {
		var opts = $.data(jq, "datagrid").options;
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
						$(jq).datagrid("loaded");
					}, 0);
					renderGrid(jq, data);
					setTimeout(function() {
						resetOperation(jq);
					}, 0);
				},
				error : function() {
					setTimeout(function() {
						$(jq).datagrid("loaded");
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
			
			var state = $.data(this, "datagrid");
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
				$.data(this, "datagrid", {
					options : opts,
					panel : gridWrap.panel,
					dc : gridWrap.dc,
					selectedRows : [],
					data : {
						total : 0,
						rows : []
					},
					originalRows : [],
					updatedRows : [],
					insertedRows : [],
					deletedRows : []
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
	$.fn.cardview.methods = {
		options : function(jq) {
			var gridOpts = $.data(jq[0], "datagrid").options;
			var panelOpts = $.data(jq[0], "datagrid").panel.panel("options");
			var opts = $.extend(gridOpts, {
				width : panelOpts.width,
				height : panelOpts.height,
				closed : panelOpts.closed,
				collapsed : panelOpts.collapsed,
				minimized : panelOpts.minimized,
				maximized : panelOpts.maximized
			});
			var pager = jq.datagrid("getPager");
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
			return $.data(jq[0], "datagrid").panel;
		},
		getPager : function(jq) {
			return $.data(jq[0], "datagrid").panel.find("div.datagrid-pager");
		},
		resize : function(jq, param) {
			return jq.each(function() {
				setSize(this, param);
			});
		},
		load : function(jq, param) {
			return jq.each(function() {
				var opts = $(this).datagrid("options");
				opts.pageNumber = 1;
				var pager = $(this).datagrid("getPager");
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
				var opts = $.data(this, "datagrid").options;
				$(this).datagrid("getPager").pagination("loading");
				if (opts.loadMsg) {
					var panel = $(this).datagrid("getPanel");
					$("<div class=\"datagrid-mask\" style=\"display:block\"></div>").appendTo(panel);
					$("<div class=\"datagrid-mask-msg\" style=\"display:block\"></div>").html(opts.loadMsg).appendTo(panel);
					setMsgSize(this);
				}
			});
		},
		loaded : function(jq) {
			return jq.each(function() {
				$(this).datagrid("getPager").pagination("loaded");
				var panel = $(this).datagrid("getPanel");
				panel.children("div.datagrid-mask-msg").remove();
				panel.children("div.datagrid-mask").remove();
			});
		},
		loadData : function(jq, data) {
			return jq.each(function() {
				renderGrid(this, data);
				resetOperation(this);
			});
		},
		getData : function(jq) {
			return $.data(jq[0], "datagrid").data;
		},
		getRows : function(jq) {
			return $.data(jq[0], "datagrid").data.rows;
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
		},
		refreshRow : function(jq, index) {
			return jq.each(function() {
				var opts = $.data(this, "datagrid").options;
				opts.view.refreshRow.call(opts.view, this, index);
			});
		},
		updateRow : function(jq, param) {
			return jq.each(function() {
				var opts = $.data(this, "datagrid").options;
				opts.view.updateRow.call(opts.view, this, param.index,param.row);
			});
		},
		appendRow : function(jq, row) {
			return jq.each(function() {
				appendRow(this, row);
			});
		},
		insertRow : function(jq, param) {
			return jq.each(function() {
				insertRow(this, param);
			});
		},
		deleteRow : function(jq, index) {
			return jq.each(function() {
				deleteRow(this, index);
			});
		}
	};
	$.fn.cardview.parseOptions = function(jq) {
		var t = $(jq);
		return $.extend({},$.fn.panel.parseOptions(jq),{
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
			remoteSort : (t.attr("remoteSort") ? t
					.attr("remoteSort") == "true" : undefined),
			colNums : t.attr("colNums"),
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
			var opts = $.data(jq, "datagrid").options;
			var rows = $.data(jq, "datagrid").data.rows;
			var cols = opts.colNums;
			var i;
			var width = 100/cols + '%';
			var html = [ "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" ];
			for (i = 0; i < rows.length; i++) {
				if(i%cols == 0){
					html.push('<tr>');
				}
				html.push('<td width="' + width + '" datagrid-row-index="' + i + '"');
				var style = opts.rowStyler ? opts.rowStyler.call(jq, i,rows[i]) : "";
				if(style){
					html.push(' style="' + style + '"');
				}
				html.push('>');
				html.push(this.renderRow.call(this, jq, i, rows[i]));
				html.push('</td>');
				if((i+1)%cols == 0){
					html.push('</tr>');
				}
			}
			if(i%cols != 0){
				while(i%cols != 0){
					html.push('<td width="' + width + '"></td>');
					i++;
				}
				html.push('</tr>');
			}
			html.push("</tbody></table>");
			$(container).html(html.join(""));
		},
		renderRow : function(jq, rowIndex, rowData) {
			var opts = $.data(jq, "datagrid").options;
			var cc = [];
			cc.push(JSON.stringify(rowData));
			return cc.join("");
		},
		refreshRow : function(jq, rowIndex) {
			var row = {};
			var rows = $(jq).datagrid("getRows");
			$.extend(row, rows[rowIndex]);
			this.updateRow.call(this, jq, rowIndex, row);
		},
		updateRow : function(jq, rowIndex, row) {
			var opts = $.data(jq, "datagrid").options;
			var rows = $(jq).datagrid("getRows");
			var tr = opts.finder.getTr(jq, rowIndex);
			tr.html(this.renderRow.call(this, jq, rowIndex, row));
		},
		insertRow : function(jq, rowIndex, row) {
			var opts = $.data(jq, "datagrid").options;
			var dc = $.data(jq, "datagrid").dc;
			var data = $.data(jq, "datagrid").data;
			if (rowIndex == undefined || rowIndex == null) {
				rowIndex = data.rows.length;
			}
			if (rowIndex > data.rows.length) {
				rowIndex = data.rows.length;
			}
			for ( var i = data.rows.length - 1; i >= rowIndex; i--) {
				opts.finder.getTr(jq, i, "body", 2).attr(
						"datagrid-row-index", i + 1);
				var tr = opts.finder.getTr(jq, i, "body", 1).attr(
						"datagrid-row-index", i + 1);
				if (opts.rownumbers) {
					tr.find("div.datagrid-cell-rownumber").html(i + 2);
				}
			}
			var frozenFields = $(jq).datagrid("getColumnFields", true);
			var fields = $(jq).datagrid("getColumnFields", false);
			var tr1 = "<tr datagrid-row-index=\"" + rowIndex + "\">"
					+ this.renderRow.call(this, jq, frozenFields, true, rowIndex, row)
					+ "</tr>";
			var tr2 = "<tr datagrid-row-index=\"" + rowIndex + "\">"
					+ this.renderRow.call(this, jq, fields, false, rowIndex, row)
					+ "</tr>";
			if (rowIndex >= data.rows.length) {
				if (data.rows.length) {
					opts.finder.getTr(jq, "", "last", 1).after(tr1);
					opts.finder.getTr(jq, "", "last", 2).after(tr2);
				} else {
					dc.body1.html("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" + tr1 + "</tbody></table>");
					dc.body2.html("<table cellspacing=\"0\" cellpadding=\"0\" border=\"0\"><tbody>" + tr2 + "</tbody></table>");
				}
			} else {
				opts.finder.getTr(jq, rowIndex + 1, "body", 1).before(tr1);
				opts.finder.getTr(jq, rowIndex + 1, "body", 2).before(tr2);
			}
			data.total += 1;
			data.rows.splice(rowIndex, 0, row);
			this.refreshRow.call(this, jq, rowIndex);
		},
		deleteRow : function(jq, rowIndex) {
			var opts = $.data(jq, "datagrid").options;
			var data = $.data(jq, "datagrid").data;
			opts.finder.getTr(jq, rowIndex).remove();
			for ( var i = rowIndex + 1; i < data.rows.length; i++) {
				opts.finder.getTr(jq, i, "body", 2).attr(
						"datagrid-row-index", i - 1);
				var tr1 = opts.finder.getTr(jq, i, "body", 1).attr(
						"datagrid-row-index", i - 1);
			}
			data.total -= 1;
			data.rows.splice(rowIndex, 1);
		},
		onBeforeRender : function(jq, rows) {
		},
		onAfterRender : function(jq) {
			var opts = $.data(jq, "datagrid").options;
			if (opts.showFooter) {
				var footer = $(jq).datagrid("getPanel").find("div.datagrid-footer");
				footer.find("div.datagrid-cell-rownumber,div.datagrid-cell-check").css("visibility", "hidden");
			}
		}
	};
	$.fn.cardview.defaults = $.extend({},$.fn.panel.defaults,{
     	colNums: 1,
		toolbar : null,
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
		showHeader : false,
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
			getTr : function(jq, rowIndex, type, step) {
				type = type || "body";
				step = step || 0;
				var dc = $.data(jq, "datagrid").dc;
				var opts = $.data(jq, "datagrid").options;
				if (step == 0) {
					var tr2 = opts.finder.getTr(jq, rowIndex,type, 2);
					return tr2;
				} else {
					if (type == "body") {
						return dc.body2
								.find(">table>tbody>tr>td[datagrid-row-index=" + rowIndex + "]");
					} else {
						if (type == "footer") {
							return dc.footer2.find(">table>tbody>tr>td[datagrid-row-index=" + rowIndex + "]");
						} else {
							if (type == "selected") {
								return dc.body2.find(">table>tbody>tr>td.datagrid-row-selected");
							} else {
								if (type == "last") {
									return dc.body2.find(">table>tbody>tr>td:last[datagrid-row-index]");
								} else {
									if (type == "allbody") {
										return dc.body2.find(">table>tbody>tr>td[datagrid-row-index]");
									} else {
										if (type == "allfooter") {
											return dc.footer2.find(">table>tbody>tr>td[datagrid-row-index]");
										}
									}
								}
							}
						}
					}
				}
			},
			getRow : function(jq, rowIndex) {
				return $.data(jq, "datagrid").data.rows[rowIndex];
			}
		},
		view : view,
		onBeforeLoad : function(param) {
		},
		onLoadSuccess : function() {
		},
		onLoadError : function() {
		},
		onClickRow : function(rowIndex, rowData) {
		},
		onDblClickRow : function(rowIndex, rowData) {
		},
		onClickCell : function(rowIndex, field, value) {
		},
		onDblClickCell : function(rowIndex, field, value) {
		},
		onSelect : function(rowIndex, rowData) {
		},
		onUnselect : function(rowIndex, rowData) {
		},
		onSelectAll : function(rows) {
		},
		onUnselectAll : function(rows) {
		},
		onRowContextMenu : function(e, owIndex, rowData) {
		}
	});
})(jQuery);