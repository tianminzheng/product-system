var config = {};

config.api = [
{
	module : "Order",
	payload : [{
		name : "根据Id获取Order信息",
		id : "getOrderById",
		path : "order/{orderId}",
		path2 : "order/1",
		method : "get",
		params: {}
	}
	]
}
];

var _attachId = function(api) {
	for (var i = 0; i < api.length; i++) {
		var mid = "m" + i;
		api[i].id = mid;
		var pd = api[i].payload;
		for (var j = 0; j < pd.length; j++) {
			var aid = "i" + j;
			pd[j].id = mid + aid;

		}
	}

};
_attachId(config.api);
