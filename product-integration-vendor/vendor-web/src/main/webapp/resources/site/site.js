var telemed = (function($) {
	
	var _hasArray = function(obj) {
		var ha = false;
		if ($.isArray(obj)) {
			return true;
		}
		$.each(obj, function(name, value) {
			if (typeof value === "object") {
				ha = true;
				return ha;
			}
		});
		return ha;
	};


	// 构建 ajax 请求
	var mkrq = function(pi) {
		var ps = {
			type : pi.method,
			url : pi.path2,
			data : pi.params
		};
		if (_hasArray(pi.params)) {
			console.log(pi.name + " Array");
			ps.contentType = "application/json; charset=UTF-8";
			ps.data = JSON.stringify(pi.params);
		}
		return function() {
			$.ajax(ps).done(function(data, textStatus, jqxhr) {
				
				var rt = jqxhr.getResponseHeader('Content-Type');
				if (rt&&rt.match('image')) {
					$('#c' + pi.id).html('<img src="$src"></img>'.replace("$src", pi.path2+"?"+$.param(pi.params)));
				} else {
					$('#c' + pi.id).html('<pre>$o</pre>'.replace("$o", JSON.stringify(data, undefined, 1)));
				}
				
			}).fail(function(jqxhr, textStatus, error) {
				var data = jqxhr.responseJSON;
				var err = textStatus + ", " + error;
				console.log("Request Failed: " + err);
				$('#c' + pi.id).text(JSON.stringify(data, undefined, 1));
			});
		};

	};


	var renderapi = function(testc) {

		var url = "http://127.0.0.1:8081/client-web/";
		// 修改地址
		var $payload = $('#payload');
		var ss = "";
		for (var k = 0; k < testc.length; k++) {
			ss += '<h2 id = "module$i">$md</h2>'.replace('module$i', testc[k].id).replace(
					'$md', testc[k].module);
			var section = "";
			var payload = testc[k].payload;
			for (var i = 0; i < payload.length; i++) {
				var pi = payload[i];
				section += '<section><h3 id="' + pi.id + '">';
				section += (i + 1) + ". " + pi.name + "   " + url + pi.path
						+ "  " + pi.method;
				section += '</h3>';
				if (pi.params) {
					section += '<h4>输入参数</h4>';
					section += '<pre>';
					section += JSON.stringify(pi.params, undefined, 1);
					section += '</pre>';
				}
				section += '<h4>返回结果</h4>';
				section += '<div id="c' + pi.id + '">';
				section += '</div></section>';
			}
			;
			ss += section;
		}
		$payload.append(ss);
		for (var k = 0; k < testc.length; k++) {
			var payload = testc[k].payload;
			for (i = 0; i < payload.length; i++) {
				pi = payload[i];
				console.log(pi);
				mkrq(pi)();
			}
			;
		}
		;

	};

	return {
		renderapi : renderapi
	};
})(jQuery);
