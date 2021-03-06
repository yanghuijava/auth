function showMessager(msg) {
	$.messager.show({
        title:'提示',
        msg:msg,
        timeout:3000,
        showType:'show'
    });
}

function templateDownload(fileName){
	window.location = 'templatedownload/' + fileName + '.html';
}


$.ajaxSetup({   
	error: function (XMLHttpRequest, textStatus, errorThrown){  
		if(XMLHttpRequest.status==403){  
			alert('您没有权限访问此资源或进行此操作');  
			return false;  
		}  
	},    
	complete:function(XMLHttpRequest,textStatus){     
		var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus"); //通过XMLHttpRequest取得响应头,sessionstatus，   
        	if(sessionstatus=='timeout'){     
	            //如果超时就处理 ，指定要跳转的页面    
	        	var top = getTopWinow(); //获取当前页面的顶层窗口对象  
	        	$.messager.confirm('提示','登录超时, 请重新登录?',function(r){
	        	    if (r){
	        	    	top.location.href = "login.html";
	        	    }
	        	});
	        	//alert('登录超时, 请重新登录.');
	            //top.location.href = "login.html"; //跳转到登陆页面  
        	}     
     	}
});   
	  
/**  
  * 在页面中任何嵌套层次的窗口中获取顶层窗口  
  * @return 当前页面的顶层窗口对象  
  */  
function getTopWinow(){    
    var p = window;    
    while(p != p.parent){    
        p = p.parent;    
    }    
    return p;    
} 
