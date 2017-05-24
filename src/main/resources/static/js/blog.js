$(function(){
	//加载页头
	$("#mainNav").load("nav");
	//加载页脚
	$("#footer").load("footer");
	//加载文章
	var titles = "";
	$("#titles").empty();
	$.ajax({
		url:"article/getArticlesByPage/1",
		type:"get",
		dataType:"json",
		success:function(data){
			for(var i=0;i<data.content.length;i++){
	      		titles+='<a href="post.html">';
	      		titles+=' <h2 class="post-title">';
	      		titles+=data.content[i].title;
	      		titles+='</h2>';
	      		titles+=' </a>';
	      		titles+='<p class="post-meta">';
	      		titles+=data.content[i].createDate;
	      		titles+='</p>';
	      		titles+='<hr></hr>';
			}
			$("#titles").append(titles);
			$('#pages').bootstrapPaginator({    
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
			        				$("#titles").empty();
			        				titles="";
			        				var articleListLength = data.content.length;
			        				for(var i=0;i<articleListLength;i++){
			        					titles+='<a href="post.html">';
			        		      		titles+=' <h2 class="post-title">';
			        		      		titles+=data.content[i].title;
			        		      		titles+='</h2>';
			        		      		titles+='</a>';
			        		      		titles+='<p class="post-meta">';
			        		      		titles+=data.content[i].createDate;
			        		      		titles+='</p>';
			        		      		titles+='<hr></hr>';
			        				}
			        				$("#titles").append(titles);
			        		}
			        	})
				    }
				});
		}
	})
})