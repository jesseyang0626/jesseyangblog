$(function(){
	var titles = "";
	/*<![CDATA[*/
//加载编辑器

var editor = new wangEditor('editor');
editor.config.uploadImgUrl ='/img/upload';
editor.config.uploadImgFileName = 'file';
editor.create();


//加载文章列表
$.ajax({
	url:"article/page/1",
	dataType:"json",
	success:function(data){
			$("#articleTitles").empty();
			titles="";
			var articleListLength = data.content.length;
			for(var i=0;i<articleListLength;i++){
				var date = data.content[i].createDate.substring(0,11);
				titles += '<span id="'+data.content[i].id+'" style="display:inline">';
				titles += '<a href="/article/'+data.content[i].id+'">' +data.content[i].title+'</a></span>';
				//添加编辑和删除按钮
				titles += '<button id="delbtn" class="btn btn-default" >删除</button><br/>';

			}
			$("#articleTitles").html(titles);
			$('#pageLimit').bootstrapPaginator({    
			    currentPage: data.number+1,    
			    totalPages: data.totalPages,    
			    size:"normal",    
			    bootstrapMajorVersion: 3,    
			    alignment:"center",    
			    numberOfPages:5, 
			    itemTexts: function (type, page, current) {        
			        switch (type) {            
			        case "first": return "首页";            
			        case "prev": return "上一页";            
			        case "next": return "下一页";            
			        case "last": return "末页";            
			        case "page": return page;
			        }
			    },
			    onPageClicked:function(event, originalEvent, type, page){
			        	$.ajax({
			        		url:"article/page/"+page,
			        		dataType:"json",
			        		success:function(data){
			        				$("#articleTitles").empty();
			        				titles="";
			        				var articleListLength = data.content.length;
			        				for(var i=0;i<articleListLength;i++){
			        					var date = data.content[i].createDate.substring(0,11);
			        					titles += '<span id="'+data.content[i].id+'" style="display:inline">';
			        					titles += '<a href="/article/'+data.content[i].id+'">' +data.content[i].title+'</a></span>';
			        					//添加编辑和删除按钮
			        					titles += '<button id="delbtn" class="btn btn-default" >删除</button><br/>'
			        				}
			        				$("#articleTitles").html(titles);
			        		}
			        	})
				    }
				});
	}
});



//delete by id
$("#articleTitles").on("click","#delbtn",function(){
	console.log($(this).prev().attr("id"));
	var id = $(this).prev().attr("id");
	var flag = confirm("ok吗");
	if(flag == true){
		$.ajax({
			url:"/article/"+id,
			type:"delete",
			success:function(data){
				if(data == 1){
					alert("删了");
				}
				location.reload() ;
			}
		});
	}else{
		
	}
	
})

   //提交
    $('#btn').click(function () {
        // 获取编辑器区域完整html代码
        var html = editor.$txt.html();

        // 获取编辑器纯文本内容
        var text = editor.$txt.text();

        // 获取格式化后的纯文本
        var formatText = editor.$txt.formatText();
        
        //获取下拉框内容
        var selectType = $("#articleType").val();
        var selectTypeText = $("#articleType").find("option:selected").text();
        
        //获得标题
        var title = $("#title").val();
        console.log(title);
        console.log(html);
        console.log(selectType);
        console.log(selectTypeText); 	
        var json={};
        json.title=title;
        json.content=html;
        json.type=selectTypeText;
	    if(title=""){
	    	alert("标题为空");
	    }else if(html=""){
	    	alert("内容为空");
	    }else{
	    	 $.ajax({
		        	url:"/article/add",
		        	type:"post",
		        	data:JSON.stringify(json),
		            contentType:"application/json;charset=utf-8",
		        	success:function(data){
		        		if(data==1){
		        			alert("新增成功");
		        			location.reload() ;
		        		}else{
		        			alert("没保存上");
		        		}
		        	}
		        });
	    }
    });

//给编辑器追加内容
$('#aaa').click(function () {
	 editor.$txt.html(aaa);
});
/*]]>*/
});