$(function(){
	//加载页脚
	$("#mainNav").load("nav");
	//加载页脚
	$("#footer").load("footer");
	//加载文章列表
	var content = "";
	$.ajax({
		url:"/article/getTop6",
		type:"get",
		dataType:"json",
		success:function(data){
			for(var i=0;i<data.content.length;i++){
				content +='<div class="col-lg-10 offset-lg-1 col-md-10 offset-md-1">';
				content +=' <div class="post-preview">';
				content +='<a href="post.html">';
				content +='<h2 class="post-title">';
				content +=data.content[i].title;
				content +='</h2>';
				content +='<h3 class="post-subtitle">';
				// 去掉转义字符  
			    var s = data.content[i].content.replace(/[\'\"\\\/\b\f\n\r\t]/g, '');  
			    // 去掉特殊字符  
			    s = s.replace(/[&\|\<>\\*^%$#@\-]/g,"");
				content +=s.substring(0,30);
				content +=' .......</h3>';
				content +=' </a>';
				content +=' <p class="post-meta">'+data.content[i].createDate +'</p>';
				content +='</div>';
				content +='<hr></hr>';
				content +='</div>';
			}
			content +='<div class="col-lg-10 offset-lg-1 col-md-10 offset-md-1">';
			content +='<div class="clearfix float-right">';
			content +='<a class="btn btn-secondary float-right" href="#">Older Posts &rarr;</a>';
			content +='</div>';
			content +='</div>';
			$("#titlerow").append(content);
		}
	})
	
	
})