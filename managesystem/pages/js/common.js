function getCurrentDateTime() {
  var date = new Date();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hours = date.getHours();
  var minutes = date.getMinutes();
  var seconds = date.getSeconds();
  return year + "-" + formatZero(month) + "-" + formatZero(day) + " "
      + formatZero(hours) + ":" + formatZero(minutes) + ":" + formatZero(
          seconds);
}

function getCurrentDate() {
  var date = new Date();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  return year + "-" + formatZero(month) + "-" + formatZero(day);
}

function formatZero(n) {
  if (n >= 0 && n <= 9) {
    return "0" + n;
  } else {
    return n;
  }
}

$.extend($.fn.datagrid.defaults, {
  // loader 使用json数据提交
  loader: function (param, success, error) {
    var opts = $(this).datagrid('options');
    if (!opts.url) {
      return false;
    }
    $.ajax({
      type: opts.method,
      url: opts.url,
      contentType: 'application/json; charset=utf-8;',
      data: formParam2JsonString(param),
      dataType: 'json',
      success: function (data) {
        success(data);
      },
      error: function (jqXHR, textStatus, errorThrown){
        error(jqXHR, textStatus, errorThrown);
      }
    });
  },
  // ajaxError 也会触发，这里也会触发，重复提示
  onLoadError: function (data) {
    // $.messager.alert('提示信息', '请求异常', 'info');
    $(this).datagrid('loadData', {total: 0, rows: []});
  }
  // ajax 提交 500 后， 先走loadFilter 然后走onLoadError
  // loadFilter: function (data) {
  //   console.log("loadFilter");
  //   console.log(data);
  //   if (data.code != 0) {
  //     var push = {
  //       "total": 0,
  //       "rows": ""// 当为[]会出现空的两行
  //     };
  //     $.messager.alert("请求失败", data.message);
  //     return push;
  //   } else {
  //     return data;
  //   }
  // },
});

$(document).ajaxError(function (e, xhr) {
  let data = JSON.parse(xhr.responseText)
  $.messager.alert("请求失败", data.message);
});

function form2JsonString(formId) {
  // 序列化成 key=value&key1=value1 的字符串
  let paramArray = $('#' + formId).serializeArray();
  let jsonObj = {};
  $(paramArray).each(function () {
    jsonObj[this.name] = this.value;
  });
  // json对象再转换成json字符串
  return JSON.stringify(jsonObj);
}

/*
 * datagrid 返回的数据已经是json格式了，转成字符串
 */
function formParam2JsonString(formParam) {
  return JSON.stringify(formParam);
}