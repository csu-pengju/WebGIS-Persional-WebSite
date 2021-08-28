<%--
  Created by IntelliJ IDEA.
  User: Daisy
  Date: 2020/5/17
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Daisy's Website </title>
    <meta charset="UTF-8">
    <meta name="referrer" content="no-referrer">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    <script src=WEB-INF/support/jquery-3.4.1.min.js"></script>
    <script src="static/js/ArticleControl.js"></script>
    <link rel="stylesheet" href="static/css/index.css">
    <link rel="apple-touch-icon" type="image/png" href="https://cpwebassets.codepen.io/assets/favicon/apple-touch-icon-5ae1a0698dcc2402e9712f7d01ed509a57814f994c660df9f7a952f3060705ee.png">
    <meta name="apple-mobile-web-app-title" content="CodePen">
    <link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">
    <link rel="mask-icon" type="" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">
    <title>CodePen - Dodecahedron</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <link rel="stylesheet" href="static/sass/three.css">
    <link rel="stylesheet" href="static/css/index_content.css">
    <script>
      window.console = window.console || function(t) {};
    </script>
    <script>
      if (document.location.search.match(/type=embed/gi)) {
        window.parent.postMessage("resize", "*");
      }
    </script>
  </head>
  <body>
  <div class="wrapper">
    <div class="header">
      <div class="header_left">
        <nav class="main_menu_left_side">
          <ul id="ul_menu_left_menu">
            <li class="nav_menu_item" id="nav_menu_item_1">
              <a href="#">
                <span>HOME</span>
              </a>
            </li>
            <li class="nav_menu_item" id="nav_menu_item_2">
              <a href="# ">
                <span>ABOUT</span>
              </a>
            </li>
            <li class="nav_menu_item" id="nav_menu_item_3">
              <a href="static/html/ProjectDirectory.html">
                <span>PROJECT</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
      <div class="header_middle">
        <div class="circle1">
          <p>Daisy</p>
<%--          <p>Pj</p>--%>
        </div>
      </div>
      <div class="header_right">
        <nav class="main_menu_right_side">
          <ul id="ul_menu_right_menu">
            <li class="nav_menu_item" id="nav_menu_item_4">
              <a href="static/html/blog_list.html">
                <span>BLOG</span>
              </a>
            </li>
            <li class="nav_menu_item" id="nav_menu_item_5">
              <a href="#">
                <span>MUSIC</span>
              </a>
            </li>
            <li class="nav_menu_item" id="nav_menu_item_6">
              <a href="#">
                <span>CONTACT</span>
              </a>
            </li>
          </ul>
        </nav>
      </div>
    </div>
    <div class="three_CSS">
      <div class="view">
        <div class="plane main">
          <div class="circle"></div>
          <div class="circle"></div>
          <div class="circle"></div>
          <div class="circle"></div>
          <div class="circle"></div>
          <div class="circle"></div>
        </div>
    </div>
  </div>
  </div>
  <div class="content">
  <div class="content-left blogs">
    <p class="latest">最新文章</p>
    <ul class="latest-ul">
      <li>
        <div class="blog" >
          <div class="blog-title-box">
            <div class="blog-id"  >
              <span class="date-day">26</span>
              <span class="date-month">Aug</span>
            </div>
            <div class="blog-title"  >
              <p>
                <a href="#" >城市交通建模</a>
              </p>
              <span class="blog_author_time" style="height: 20px">
                <span class="blog-author">by Daisy</span>
                <span class="blog-time">2021-08-26</span>
              </span>
            </div>
          </div>
          <div class="blog-abstract-box">
            <p class="blog-abstract">利用GPS轨迹数据提取路段速度,通过多头自注意机制结合道路语义信息补全城市路网中在若干时刻的缺失----------</p>
          </div>
          </div>
      </li>
      <li>
        <div class="blog">
          <div class="blog-title-box">
            <span class="blog-id">1. </span>
            <span class="blog-title">城市交通建模</span>
          </div>
          <div class="blog-abstract-box">
            <p class="blog-abstract">利用GPS轨迹数据提取路段速度,通过多头自注意机制结合道路语义信息补全城市路网中在若干时刻的缺失----------</p>
          </div>
        </div>
      </li>
      <li>
        <div class="blog">
          <div class="blog-title-box">
            <span class="blog-id">1. </span>
            <span class="blog-title">城市交通建模</span>
          </div>
          <div class="blog-abstract-box">
            <p class="blog-abstract">利用GPS轨迹数据提取路段速度,通过多头自注意机制结合道路语义信息补全城市路网中在若干时刻的缺失----------</p>
          </div>
        </div>
      </li>
    </ul>
  </div>

  <div content="content-right projects">
    <p class="latest">最新项目</p>
  </div>
  </div>
  <div class="footer">
    <div class="footer-contact">
      <div class="myAddress">
        <p>Address : Hunan changsha Centrol South University,China</p>
        <p>Telephone : 13278884937</p>
        <p>Email : 2637394747@qq.com/daisy_pj@csu.edu.cn</p>
      </div>
      <div class="social-icon">
        <img src="static/images/Facebook.png">
        <img src="static/images/twitter.png">
        <img src="static/images/webo.png">
          <img src="static/images/CN_csdn.net.png">
      </div>
    </div>
    <div class="footer-copyright">
      <span>@ Copyright 2020 DaisyPj</span>
    </div>
    </div>
  <button class="write_blog"><a href="static/html/edit_page.html" style=" color: whitesmoke; ">写博客</a></button>
  <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-8216c69d01441f36c0ea791ae2d4469f0f8ff5326f00ae2d00e4bb7d20e24edb.js"></script>
  <script id="rendered-js">
    (function() {
    }).call(this);
    var articleControl = new ArticleControl({
      div: "latest-ul"
    });
  </script>
  </body>
</html>


