$(document).ready(function(){
    $('#filer_input').filer({
        limit: null,
        maxSize: 3,
        extensions: ['gpx', 'ini', 'txt', 'gif'],
        onEmpty: {
			alert: function(text) {
				return alert("onEmpty alert2" + text);
			}
        }
    });
});
