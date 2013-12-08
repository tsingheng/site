/*!
* jQuery Form Plugin
* version: 2.43 (12-MAR-2010)
* @requires jQuery v1.3.2 or later
*/
; (function($) {
    $.fn.ajaxSubmit = function(options) {
        if (!this.length) { log('ajaxSubmit: skipping submit process - no element selected'); return this; }
        if (typeof options == 'function')
            options = { success: options }; var url = $.trim(this.attr('action')); if (url) { url = (url.match(/^([^#]+)/) || [])[1]; }
        url = url || window.location.href || ''; options = $.extend({ url: url, type: this.attr('method') || 'GET', iframeSrc: /^https/i.test(window.location.href || '') ? 'javascript:false' : 'about:blank' }, options || {}); var veto = {}; this.trigger('form-pre-serialize', [this, options, veto]); if (veto.veto) { log('ajaxSubmit: submit vetoed via form-pre-serialize trigger'); return this; }
        if (options.beforeSerialize && options.beforeSerialize(this, options) === false) { log('ajaxSubmit: submit aborted via beforeSerialize callback'); return this; }
        var a = this.formToArray(options.semantic); if (options.data) {
            options.extraData = options.data; for (var n in options.data) {
                if (options.data[n] instanceof Array) {
                    for (var k in options.data[n])
                        a.push({ name: n, value: options.data[n][k] });
                }
                else
                    a.push({ name: n, value: options.data[n] });
            }
        }
        if (options.beforeSubmit && options.beforeSubmit(a, this, options) === false) { log('ajaxSubmit: submit aborted via beforeSubmit callback'); return this; }
        this.trigger('form-submit-validate', [a, this, options, veto]); if (veto.veto) { log('ajaxSubmit: submit vetoed via form-submit-validate trigger'); return this; }
        var q = $.param(a); if (options.type.toUpperCase() == 'GET') { options.url += (options.url.indexOf('?') >= 0 ? '&' : '?') + q; options.data = null; }
        else
            options.data = q; var $form = this, callbacks = []; if (options.resetForm) callbacks.push(function() { $form.resetForm(); }); if (options.clearForm) callbacks.push(function() { $form.clearForm(); }); if (!options.dataType && options.target) { var oldSuccess = options.success || function() { }; callbacks.push(function(data) { var fn = options.replaceTarget ? 'replaceWith' : 'html'; $(options.target)[fn](data).each(oldSuccess, arguments); }); }
        else if (options.success)
            callbacks.push(options.success); options.success = function(data, status, xhr) {
                for (var i = 0, max = callbacks.length; i < max; i++)
                    callbacks[i].apply(options, [data, status, xhr || $form, $form]);
            }; var files = $('input:file', this).fieldValue(); var found = false; for (var j = 0; j < files.length; j++)
            if (files[j])
            found = true; var multipart = false; if ((files.length && options.iframe !== false) || options.iframe || found || multipart) {
            if (options.closeKeepAlive)
                $.get(options.closeKeepAlive, fileUpload); else
                fileUpload();
        }
        else
        	alert(JSON.stringify(options));
            $.ajax(options); this.trigger('form-submit-notify', [this, options]); return this; function fileUpload() {
                var form = $form[0]; if ($(':input[name=submit]', form).length) { alert('Error: Form elements must not be named "submit".'); return; }
                var opts = $.extend({}, $.ajaxSettings, options); var s = $.extend(true, {}, $.extend(true, {}, $.ajaxSettings), opts); var id = 'jqFormIO' + (new Date().getTime()); var $io = $('<iframe id="' + id + '" name="' + id + '" src="' + opts.iframeSrc + '" onload="(jQuery(this).data(\'form-plugin-onload\'))()" />'); var io = $io[0]; $io.css({ position: 'absolute', top: '-1000px', left: '-1000px' }); var xhr = { aborted: 0, responseText: null, responseXML: null, status: 0, statusText: 'n/a', getAllResponseHeaders: function() { }, getResponseHeader: function() { }, setRequestHeader: function() { }, abort: function() { this.aborted = 1; $io.attr('src', opts.iframeSrc); } }; var g = opts.global; if (g && !$.active++) $.event.trigger("ajaxStart"); if (g) $.event.trigger("ajaxSend", [xhr, opts]); if (s.beforeSend && s.beforeSend(xhr, s) === false) { s.global && $.active--; return; }
                if (xhr.aborted)
                    return; var cbInvoked = false; var timedOut = 0; var sub = form.clk; if (sub) { var n = sub.name; if (n && !sub.disabled) { opts.extraData = opts.extraData || {}; opts.extraData[n] = sub.value; if (sub.type == "image") { opts.extraData[n + '.x'] = form.clk_x; opts.extraData[n + '.y'] = form.clk_y; } } }
                function doSubmit() {
                    var t = $form.attr('target'), a = $form.attr('action'); form.setAttribute('target', id); if (form.getAttribute('method') != 'POST')
                        form.setAttribute('method', 'POST'); if (form.getAttribute('action') != opts.url)
                        form.setAttribute('action', opts.url); if (!opts.skipEncodingOverride) { $form.attr({ encoding: 'multipart/form-data', enctype: 'multipart/form-data' }); }
                    if (opts.timeout)
                        setTimeout(function() { timedOut = true; cb(); }, opts.timeout); var extraInputs = []; try {
                        if (opts.extraData)
                            for (var n in opts.extraData)
                            extraInputs.push($('<input type="hidden" name="' + n + '" value="' + opts.extraData[n] + '" />').appendTo(form)[0]); $io.appendTo('body'); 
                            $io.data('form-plugin-onload', cb); 
                            form.submit();
                    }
                    finally { form.setAttribute('action', a); t ? form.setAttribute('target', t) : $form.removeAttr('target'); $(extraInputs).remove(); }
                }; if (opts.forceSync)
                    doSubmit(); else
                    setTimeout(doSubmit, 10); var domCheckCount = 100; function cb() {
                        if (cbInvoked)
                            return; var ok = true; try {
                            if (timedOut) throw 'timeout'; var data, doc; doc = io.contentWindow ? io.contentWindow.document : io.contentDocument ? io.contentDocument : io.document; var isXml = opts.dataType == 'xml' || doc.XMLDocument || $.isXMLDoc(doc); log('isXml=' + isXml); if (!isXml && (doc.body == null || doc.body.innerHTML == '')) {
                                if (--domCheckCount) { log('requeing onLoad callback, DOM not available'); setTimeout(cb, 250); return; }
                                log('Could not access iframe DOM after 100 tries.'); return;
                            }
                            log('response detected'); cbInvoked = true; xhr.responseText = doc.body ? doc.body.innerHTML : null; xhr.responseXML = doc.XMLDocument ? doc.XMLDocument : doc; xhr.getResponseHeader = function(header) { var headers = { 'content-type': opts.dataType }; return headers[header]; }; if (opts.dataType == 'json' || opts.dataType == 'script') {
                                var ta = doc.getElementsByTagName('textarea')[0]; if (ta)
                                    xhr.responseText = ta.value; else {
                                    var pre = doc.getElementsByTagName('pre')[0]; if (pre)
                                        xhr.responseText = pre.innerHTML;
                                }
                            }
                            else if (opts.dataType == 'xml' && !xhr.responseXML && xhr.responseText != null) { xhr.responseXML = toXml(xhr.responseText); }
                            data = $.httpData(xhr, opts.dataType);
                        }
                        catch (e) { log('error caught:', e); ok = false; xhr.error = e; $.handleError(opts, xhr, 'error', e); }
                        if (ok) { opts.success(data, 'success'); if (g) $.event.trigger("ajaxSuccess", [xhr, opts]); }
                        if (g) $.event.trigger("ajaxComplete", [xhr, opts]); if (g && ! --$.active) $.event.trigger("ajaxStop"); if (opts.complete) opts.complete(xhr, ok ? 'success' : 'error'); setTimeout(function() { $io.removeData('form-plugin-onload'); $io.remove(); xhr.responseXML = null; }, 100);
                    }; function toXml(s, doc) {
                        if (window.ActiveXObject) { doc = new ActiveXObject('Microsoft.XMLDOM'); doc.async = 'false'; doc.loadXML(s); }
                        else
                            doc = (new DOMParser()).parseFromString(s, 'text/xml'); return (doc && doc.documentElement && doc.documentElement.tagName != 'parsererror') ? doc : null;
                    };
            };
    }; $.fn.ajaxForm = function(options) {
        return this.ajaxFormUnbind().bind('submit.form-plugin', function(e) { e.preventDefault(); $(this).ajaxSubmit(options); }).bind('click.form-plugin', function(e) {
            var target = e.target; var $el = $(target); if (!($el.is(":submit,input:image"))) {
                var t = $el.closest(':submit'); if (t.length == 0)
                    return; target = t[0];
            }
            var form = this; form.clk = target; if (target.type == 'image') { if (e.offsetX != undefined) { form.clk_x = e.offsetX; form.clk_y = e.offsetY; } else if (typeof $.fn.offset == 'function') { var offset = $el.offset(); form.clk_x = e.pageX - offset.left; form.clk_y = e.pageY - offset.top; } else { form.clk_x = e.pageX - target.offsetLeft; form.clk_y = e.pageY - target.offsetTop; } }
            setTimeout(function() { form.clk = form.clk_x = form.clk_y = null; }, 100);
        });
    }; $.fn.ajaxFormUnbind = function() { return this.unbind('submit.form-plugin click.form-plugin'); }; $.fn.formToArray = function(semantic) {
        var a = []; if (this.length == 0) return a; var form = this[0]; var els = semantic ? form.getElementsByTagName('*') : form.elements; if (!els) return a; for (var i = 0, max = els.length; i < max; i++) {
            var el = els[i]; var n = el.name; if (!n) continue; if (semantic && form.clk && el.type == "image") {
                if (!el.disabled && form.clk == el) { a.push({ name: n, value: $(el).val() }); a.push({ name: n + '.x', value: form.clk_x }, { name: n + '.y', value: form.clk_y }); }
                continue;
            }
            var v = $.fieldValue(el, true); if (v && v.constructor == Array) {
                for (var j = 0, jmax = v.length; j < jmax; j++)
                    a.push({ name: n, value: v[j] });
            }
            else if (v !== null && typeof v != 'undefined')
                a.push({ name: n, value: v });
        }
        if (!semantic && form.clk) { var $input = $(form.clk), input = $input[0], n = input.name; if (n && !input.disabled && input.type == 'image') { a.push({ name: n, value: $input.val() }); a.push({ name: n + '.x', value: form.clk_x }, { name: n + '.y', value: form.clk_y }); } }
        return a;
    }; $.fn.formSerialize = function(semantic) { return $.param(this.formToArray(semantic)); }; $.fn.fieldSerialize = function(successful) {
        var a = []; this.each(function() {
            var n = this.name; if (!n) return; var v = $.fieldValue(this, successful); if (v && v.constructor == Array) {
                for (var i = 0, max = v.length; i < max; i++)
                    a.push({ name: n, value: v[i] });
            }
            else if (v !== null && typeof v != 'undefined')
                a.push({ name: this.name, value: v });
        }); return $.param(a);
    }; $.fn.fieldValue = function(successful) {
        for (var val = [], i = 0, max = this.length; i < max; i++) {
            var el = this[i]; var v = $.fieldValue(el, successful); if (v === null || typeof v == 'undefined' || (v.constructor == Array && !v.length))
                continue; v.constructor == Array ? $.merge(val, v) : val.push(v);
        }
        return val;
    }; $.fieldValue = function(el, successful) {
        var n = el.name, t = el.type, tag = el.tagName.toLowerCase(); if (typeof successful == 'undefined') successful = true; if (successful && (!n || el.disabled || t == 'reset' || t == 'button' || (t == 'checkbox' || t == 'radio') && !el.checked || (t == 'submit' || t == 'image') && el.form && el.form.clk != el || tag == 'select' && el.selectedIndex == -1))
            return null; if (tag == 'select') {
            var index = el.selectedIndex; if (index < 0) return null; var a = [], ops = el.options; var one = (t == 'select-one'); var max = (one ? index + 1 : ops.length); for (var i = (one ? index : 0); i < max; i++) {
                var op = ops[i]; if (op.selected) {
                    var v = op.value; if (!v)
                        v = (op.attributes && op.attributes['value'] && !(op.attributes['value'].specified)) ? op.text : op.value; if (one) return v; a.push(v);
                }
            }
            return a;
        }
        return el.value;
    }; $.fn.clearForm = function() { return this.each(function() { $('input,select,textarea', this).clearFields(); }); }; $.fn.clearFields = $.fn.clearInputs = function() {
        return this.each(function() {
            var t = this.type, tag = this.tagName.toLowerCase(); if (t == 'text' || t == 'password' || tag == 'textarea')
                this.value = ''; else if (t == 'checkbox' || t == 'radio')
                this.checked = false; else if (tag == 'select')
                this.selectedIndex = -1;
        });
    }; $.fn.resetForm = function() {
        return this.each(function() {
            if (typeof this.reset == 'function' || (typeof this.reset == 'object' && !this.reset.nodeType))
                this.reset();
        });
    }; $.fn.enable = function(b) { if (b == undefined) b = true; return this.each(function() { this.disabled = !b; }); }; $.fn.selected = function(select) {
        if (select == undefined) select = true; return this.each(function() {
            var t = this.type; if (t == 'checkbox' || t == 'radio')
                this.checked = select; else if (this.tagName.toLowerCase() == 'option') {
                var $sel = $(this).parent('select'); if (select && $sel[0] && $sel[0].type == 'select-one') { $sel.find('option').selected(false); }
                this.selected = select;
            }
        });
    }; $.handleError = function(s, xhr, status, e){
    	if(s.error){
    		s.error.call(s.context || s, xhr, staturs, e);
    	}
    	if(s.global){
    		(s.context?$(s.context):$.event).trigger("ajaxEroro", [xhr, s, e]);
    	}
    }; function log() {
        if ($.fn.ajaxSubmit.debug) {
            var msg = '[jquery.form] ' + Array.prototype.join.call(arguments, ''); if (window.console && window.console.log)
                window.console.log(msg); else if (window.opera && window.opera.postError)
                window.opera.postError(msg);
        }
    };
})(jQuery);

/* jquery.formvalidator */
; (function($) {
    $.FormValidtor = function(opts) { alert(9); return this; }; $.fn.FormValidtor = function(opts) { $.options = $.extend({}, $.options, opts); init(this); return this; }
    $.fn.ReInit = function() { var key = this[0].id; $(this).removeData(key); $('*', this).removeClass('vderror').removeAttr('title'); init(this); }
    $.fn.Check = function() {
        var key = this[0].id; var rules = this.data(key); if (typeof (rules) == 'undefined') { return true; } else {
            var doc = document; var first = null; var result = true; var elems = rules.elems; var dom, elem; for (var i = 0, len = elems.length; i < len; i++) { elem = elems[i]; dom = doc.getElementById(elem); if (!execute(dom, rules[elem])) { result = false; if (first == null) { first = dom; } } }
            if (first != null) { if (!first.disabled) { first.focus(); } }
            return result;
        }
    }
    $.options = { visible: false }; function execute(o, s) {
        var result = true; var slen = s.len; $(s.format.split('/')).each(function(i, v) {
            if (v.length == 0) { return true; }
            if (!eval('f_' + v).call(this, o, s)) { result = false; return false; }
        }); if (slen != '0') {
            var len = o.value.length; var pos = slen.indexOf(','); if (pos > -1) {
                var min = slen.substring(0, pos); var max = slen.substring(pos + 1); if (min.length > 0) { if (len < parseInt(min, 10)) { result = false; } }
                if (max.length > 0) { if (len > parseInt(max, 10)) { result = false; } }
            } else { pos = slen.indexOf('|'); if (pos > -1) { if (len > 0) { var min = slen.substring(0, pos); var max = slen.substring(pos + 1); if (len != parseInt(min, 10) && len != parseInt(max, 10)) { result = false; } } } else if (len != parseInt(slen, 10)) { result = false; } }
        }
        if (result) { clearerror(o); } else { seterror(o, s); s.msg = ''; }
        return result;
    }
    function f_required(o, s) {
        var v = o.value; if (v.length == 0) { return false; }
        return true;
    }
    function f_letter(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[a-zA-Z]*$/.test(v) == false) { return false; }
        return true;
    }
    function f_number(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[0-9]*$/.test(v) == false) { return false; }
        return true;
    }
    function f_textkey(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[a-zA-Z0-9_]*$/.test(v) == false) { return false; }
        return true;
    }
    function f_email(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[\w\.-]+(\+[\w-]*)?@([\w-]+\.)+[\w-]+$/.test(v) == false) { return false; }
        return true;
    }
    function f_digit(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^([-+][1-9]+)?[0-9]+$/.test(v) == false) { return false; }
        return true;
    }
    function f_posint(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[1-9]\d*$/.test(v) == false) { return false; }
        return true;
    }
    function f_notzero(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^[^0]+$/.test(v) == false) { return false; }
        return true;
    }
    function f_decimal(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^([-+])?[0-9,]+(\.[0-9]+)?$/.test(v) == false) { return false; }
        return true;
    }
    function f_percent(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (/^((0\.[0-9]+)|([1-9][0-9]*([\.]?[0-9]+)?)|0)%$/.test(v) == false) { return false; }
        return true;
    }
    function f_date(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        if (s.arg == null) { s.arg = 'yyyy-mm-dd'; }
        var f = s.arg.toLowerCase().replace('yyyy', '(19[0-9]{2})|(2[0-9]{3})').replace('mm', '(0?[1-9]|1[012])').replace('dd', '(0?[1-9]|[12][0-9]|3[01])')
        var r = new RegExp('^' + f + '$', 'ig'); if (!r.test(v)) { return false; }; var start = $(o).attr('startDate') || o.id; var end = $(o).attr('endDate') || o.id; var startDate = $('#' + start).val().trim().replace(/-/gi, ""); var endDate = $('#' + end).val().trim().replace(/-/gi, ""); if (parseFloat(startDate) > parseFloat(endDate)) { return false; }
        return true;
    }
    function f_map(o, s) {
        var v = o.value; if (s.map == null) { return false; }
        if (s.map.length == 0) { return false; }
        var m = document.getElementById(s.map); if (m) { if (m.value != v) { return false; } } else { return false; }
        return true;
    }
    function f_fun(o, s) {
        var v = o.value; if (v.length == 0) { return true; }
        var result = true; if (s.fun == null) { return false; }
        $(s.fun.split('/')).each(function(i, v) {
            if (v.length == 0) { return true; }
            if (!eval(v).call(this, o, s)) { result = false; return false; }
        }); return result;
    }
    function seterror(o, s) {
        var t = $(o); if (!t.hasClass('vderror')) { t.addClass('vderror').attr('title', s.msg); } else { t.attr('title', s.msg); }
        if (s.pop != null & s.msg != '' & s.msg != 'Error!') { alert(s.msg); }
    }
    function clearerror(o) { var t = $(o); if (t.hasClass('vderror')) { t.removeClass('vderror').removeAttr('title'); } }
    function init(o) {
        var key = o[0].id; var rules = o.data(key); if (typeof (rules) == 'undefined') { rules = { elems: null }; var inputs = []; var select = $.options.visible ? '[vformat]:visible' : '[vformat]'; var obj = $(select, o); obj.each(function(i, v) { var ext = eval('({' + v.id + ': \'we\'})'); var setting = { format: $(v).attr('vformat') || '', msg: $(v).attr('vmsg') || 'Error!', fun: $(v).attr('vfun') || null, len: $(v).attr('vlen') || '0', map: $(v).attr('vmap') || null, arg: $(v).attr('varg') || null, pop: $(v).attr('vpop') || null }; ext[v.id] = setting; rules = $.extend(rules, ext); inputs[inputs.length] = v.id; $(v).unbind('blur').blur(function() { execute(v, setting); }); }); rules.elems = inputs; o.data(key, rules); }
        return rules;
    }
})(jQuery);

jQuery.Web = { get: function(url, data) {
    if (data && typeof (data) != 'string') { data = jQuery.param(data); }
    if (data) { url += (url.match(/\?/) ? "&" : "?") + data + '&token=' + token(); data = null; } else { url += (url.match(/\?/) ? "&" : "?") + 'token=' + token(); }
    var xhr = window.ActiveXObject ? new ActiveXObject("Microsoft.XMLHTTP") : new XMLHttpRequest(); xhr.open('GET', url, false); xhr.send(null); return xhr.responseText;
}, xml: function(url, data) {
    var doc = null; var text = jQuery.Web.get(url, data); if (document.implementation.createDocument) { var parser = new DOMParser(); doc = parser.parseFromString(text, 'text/xml'); } else if (window.ActiveXObject) { var doc = new ActiveXObject('Microsoft.XMLDOM'); doc.async = 'false'; doc.loadXML(text); }
    return doc;
}
};

String.prototype.trim = function() { return this.replace(/(^\s*)|(\s*$)/g, ''); }
Date.prototype.format = function(format) { var o = { "M+": this.getMonth() + 1, "d+": this.getDate(), "h+": this.getUTCHours(), "m+": this.getUTCMinutes(), "s+": this.getSeconds(), "q+": Math.floor((this.getMonth() + 3) / 3), "S": this.getMilliseconds() }; if (/(y+)/.test(format)) { format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length)); } for (var k in o) if (new RegExp("(" + k + ")").test(format)) { format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length)); } return format; }



