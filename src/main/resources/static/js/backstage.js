
$(function(){
	var titles = "";
	/*<![CDATA[*/
	$("#getArt").click();
	
	 var editor = new wangEditor('editor');
	    editor.config.uploadImgUrl ='/img/upload';
	    editor.config.uploadImgFileName = 'file';
	    editor.create();


$("#getArt").click(function(){
	 getArticleByPage(1);
});

function getArticleByPage(page){
	$.ajax({
		url:"article/page/"+page,
	dataType:"json",
	success:function(data){
		$("#articleTitles").empty();
		titles="";
		var articleListLength = data.content.length;
		for(var i=0;i<articleListLength;i++){
			console.log(data.content[i].createDate);
			var date = data.content[i].createDate.substring(0,11);
			/* var date = data.content[i].createDate.substring(0,11); */
			console.log(date.substring(0,11));
			titles += '<span id="'+data.content[i].id+'" style="display:inline"><a href="/article/'+data.content[i].id+'"  >' +data.content[i].title+'</a></span>';
			//添加编辑和删除按钮
			titles += '<button  id="delbtn"   class="btn btn-default" >删除</button><br/>'
		}
		console.log(titles);
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
	/* 	    pageUrl: function(type, page, current){              
		   	return "/article/page/"+page;  
	        }   */
		        onPageClicked:function(event, originalEvent, type, page){
		        	getArticleByPage(page);
			    }
			});
		}
	});
};



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
				$("#getArt").click();
			}
		});
	}else{
		
	}
	
})

   
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
        $.ajax({
        	url:"/article/add",
        	type:"post",
        	data:JSON.stringify(json),
            contentType:"application/json;charset=utf-8",
        	success:function(data){
        		if(data==1){
        			alert("新增成功");
        		}else{
        			alert("没保存上");
        		}
        	}
        });
        
    });
/*]]>*/
});