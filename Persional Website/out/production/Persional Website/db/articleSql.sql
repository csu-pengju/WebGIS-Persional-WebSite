
DROP TABLE IF EXISTS "blog_articles";

CREATE TABLE blog_articles(
	"id" serial,
	"blog_title" text not null,
	"blog_author" text not null,
	"blog_time" date not null DEFAULT (now()),
	"blog_day" text not null,
	"blog_month" text not null,
	"blog_abstract" text not null,
	"bloglink" text,
	"blog_html" text,
	"blog_md" text,
	"blog_fileName" text
);
insert into blog_articles(blog_title, blog_author, blog_day,blog_month, blog_abstract) values('dsd','dsd',
'23','Aug','dsd')
