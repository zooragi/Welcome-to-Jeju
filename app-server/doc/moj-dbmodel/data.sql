var connection = mysql.createConnection({
    host : 'you_ip',
    user : 'user',
    password : 'password',
    database : 'db',
    charset : 'utf8mb4'
});

create database emoji character set utf8mb4 collate utf8mb4_general_ci;

ALTER DATABASE jejudb CHARACTER SET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

ALTER DATABASE jejudb
CHARACTER SET = utf8mb4 
COLLATE = utf8mb4_unicode_ci;

-- 이모지
CREATE TABLE emoji (
  no    INTEGER      NOT NULL COMMENT '번호', -- no
  emoji VARCHAR(255) NOT NULL COMMENT '이모지' -- 이모지
)
COMMENT '이모지';

insert into emoji(no, emoji)
valuse(1, '✈️');
insert into emoji(no, emoji)
valuse(2, '🌴');
insert into emoji(no, emoji)
valuse(3, '🍊');
insert into emoji(no, emoji)
valuse(4, '🚢');
insert into emoji(no, emoji)
valuse(5, '🌊');
insert into emoji(no, emoji)
valuse(6, '🥜');
insert into emoji(no, emoji)
valuse(7, '🛵');
insert into emoji(no, emoji)
valuse(8, '🐟');
insert into emoji(no, emoji)
valuse(9, '🗿');
insert into emoji(no, emoji)
valuse(10, '🌠');

-- 회원
insert into jeju_user(user_no, email, password, nickname )
values(1, 'aaa@test.com', password('1111'), '감귤밭떼기');

insert into jeju_user(user_no, email, password, nickname )
values(2, 'bbb@test.com', password('1111'), '제주소년단');

insert into jeju_user(user_no, email, password, nickname )
values(3, 'ccc@test.com', password('1111'), '리슈언');

insert into jeju_user(user_no, email, password, nickname )
values(4, 'ddd@test.com', password('1111'), '특공대');

insert into jeju_user(user_no, email, password, nickname )
values(5, 'eee@test.com', password('1111'), '유니버스룸');

insert into jeju_user(user_no, email, password, nickname )
values(6, 'fff@test.com', password('1111'), '후이쩐');

-- 카테고리
insert into jeju_theme_category(category_no, name) values(1, '식당');
insert into jeju_theme_category(category_no, name) values(2, '카페');
insert into jeju_theme_category(category_no, name) values(3, '관광명소');
insert into jeju_theme_category(category_no, name) values(4, '기타');

-- 테마
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(1, 1, '애월에 가면', 0, 1, 3, 20210621);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(2, 1, '조개 맛있는 집', 0, 1, 1, 20210627);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(3, 1, '서귀포 숙소 추천', 0, 1, 4, 20210808);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(4, 2, '바람을 벗삼아 떠나보자', 0, 1, 3, 20210808);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(5, 2, '제주도 향토 음식', 0, 1, 1, 20210809);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(6, 2, '제주의 봄', 0, 1, 4, 20210822);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(7, 3, '바다가 사랑한 섬 우도', 0, 1, 3, 20210901);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(8, 3, '제주에서 꼭 먹어야하는 음식', 0, 1, 1, 20210903);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(9, 3, '화장실 깨끗한 장소', 0, 1, 4, 20210909);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(10, 4, '제주 동부 투어', 0, 1, 3, 20210921);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(11, 4, '자연광이 좋은 카페', 0, 1, 2, 20211001);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(12, 4, '해산물 맛집', 0, 1, 1, 20211009);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(13, 5, '노을에 잠기는 바다', 0, 1, 3, 20211101);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(14, 5, '반려동물과 함께 가기 좋은 카페', 0, 1, 2, 20211105);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(15, 5, '제주도민은 여기만 간다', 0, 1, 4, 20211111);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(16, 6, '2020 제주', 0, 0, 4, 20211029);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(17, 6, '인스타 감성샷 남길 때', 0, 1, 4, 20211001);
insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(18, 6, '24시간 병원', 0, 1, 2, 20211129);

insert into jeju_theme(theme_no, user_no, title, share, public, category_no, created_dt) values(19, 6, '미성년자도 달리자', 0, 1, 1,20211030);

-- 장소
insert into jeju_place(place_id, place_name, place_address,x_coord,y_coord) values('1261877281', '로맨틱새우', '제주특별자치도 제주시 애월읍 곽지리 1584-5', 126.305303780014, 33.4481834855432);
insert into jeju_place(place_id, place_name, place_address,x_coord,y_coord) values('1641211499', '도치돌목장', '제주특별자치도 제주시 애월읍 납읍리 102', 126.365809643225, 33.4099556021011);
insert into jeju_place(place_id, place_name, place_address,x_coord,y_coord) values('269602057', '오션뷰제주 게스트하우스', '제주특별자치도 서귀포시 안덕면 창천리 785-2', 126.367031286534, 33.2322187489819);
insert into jeju_place(place_id, place_name, place_address,x_coord,y_coord) values('27525363', '깡촌흑돼지', '제주특별자치도 제주시 조천읍 함덕리 1001', 126.667135470078, 33.541274383213);

-- 후기
insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(1, '1261877281', '새우집인데 조개가 존맛 ㅠㅠ', 1);
insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(2, '1641211499', '귀여운 양', 1);

insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(3, '1261877281', '새우가 맛있음!!', 4);
insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(4, '269602057', '분위기가 너무 좋다.', 4);

insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(5, '27525363', '흑돼지 존맛집!!', 2);
insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(6, '27525363', '현지인들도 매일 가는 맛집', 5);
insert into jeju_place_comment(comment_no, place_id, comment, user_no) values(7, '27525363', '주차장이 넓은 삼겹살맛집', 3);

-- 사진
insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(1, '1261877281',  '조개.gif', 1);
insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(2, '1641211499', '양.jpg', 1);

insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(3, '1261877281', '새우.jpg', 4);
insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(4, '269602057', '오션뷰.jpg', 4);

insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(5, '27525363', '돼지.jpg', 2);
insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(6, '27525363', '흑돼지.jpg', 5);
insert into jeju_place_photo(place_photo_no, place_id, file_path, user_no) values(7, '27525363', '삼겹살.gif', 3);

-- 좋아하는 유저
Insert into jeju_liked_user(user_no, user_no2) values(1, 2);
Insert into jeju_liked_user(user_no, user_no2) values(4, 3);
Insert into jeju_liked_user(user_no, user_no2) values(1, 4);
Insert into jeju_liked_user(user_no, user_no2) values(2, 5);
Insert into jeju_liked_user(user_no, user_no2) values(2, 6);
Insert into jeju_liked_user(user_no, user_no2) values(6, 1);
Insert into jeju_liked_user(user_no, user_no2) values(3, 6);
Insert into jeju_liked_user(user_no, user_no2) values(3, 5);

-- 해시태그
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(1, 1, '양들이랑');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(2, 2, '맛조개');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(3, 2, '구이탕찜');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(4, 3, '서귀포 숙소');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(5, 3, '깨끗한');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(6, 3, '안전한');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(7, 3, '자연친화적');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(8, 4, '바람따라');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(9, 4, '해안도로');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(10, 4, '패딩필수');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(11, 5, '제주도의 맛');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(12, 6, '꽃놀이');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(13, 6, '봄바람');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(14, 7, '땅콩맛집');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(15, 7, '우도 버스투어');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(15, 7, '전기자전거');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(16, 8, '현지인맛집');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(17, 8, '흑돼지');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(18, 8, '한라봉');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(19, 9, '산뜻');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(20, 9, '쾌적해');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(21, 10, '해돋이');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(22, 10, '일출');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(23, 10, '일몰');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(24, 11, '광합성이 필요해');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(25, 11, '비타민 충전');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(26, 11, '느긋하게커피한모금');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(27, 12, '둘이먹다둘다죽음');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(28, 12, '로컬맛집');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(29, 12, '해녀집');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(30, 13, '노을풍경');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(31, 13, '인스타명관');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(32, 14, '댕댕이천국');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(33, 14, '집사와주인님');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(34, 14, '퍼푸치노');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(35, 15, '찐맛집');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(36, 15, '현지인추천');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(37, 16, '2020-10-10');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(38, 16, 'with특공대');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(39, 17, '인스타갬성');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(40, 17, '감성이 살아있는 카페');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(41, 18, '응급실');
Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(42, 18, '다쳤을때');

Insert into jeju_theme_hashtag(hashtag_no, theme_no, name) value(43, 1, '새별오름');


-- 좋아하는 테마

Insert into jeju_liked_theme(user_no, theme_no) values(1, 8);
Insert into jeju_liked_theme(user_no, theme_no) values(1, 4);
Insert into jeju_liked_theme(user_no, theme_no) values(1, 10);
Insert into jeju_liked_theme(user_no, theme_no) values(2, 2);
Insert into jeju_liked_theme(user_no, theme_no) values(2, 13);
Insert into jeju_liked_theme(user_no, theme_no) values(3, 3);
Insert into jeju_liked_theme(user_no, theme_no) values(3, 10);
Insert into jeju_liked_theme(user_no, theme_no) values(3, 15);

-- 신고 상태
Insert into jeju_report_status(report_status_no, title) values(0, '확인중');
Insert into jeju_report_status(report_status_no, title) values(1, '완료');

-- 유저 신고
Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(3, 2, '댓글테러를 일삼음', 0);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(2, 4, '누가봐도알바', 0);

Insert into jeju_report_user(user_no, user_no2, content, report_status_no)
values(1, 5, '후기에 욕을..', 1);

-- 테마 신고
Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(2, 1, '겹치는 테마', 0);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(5, 2, '조개집이 아니고 고기집인데요', 1);

Insert into jeju_report_theme(user_no, theme_no, content, report_status_no)
values(3, 19, '미성년자에게 술강요', 1);

-- 장소_유저_테마
Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1261877281', 1, 8);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1261877281', 2, 15);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1261877281', 3, 12);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1261877281', 4, 2);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1641211499', 3, 1);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1641211499', 6, 15);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('27525363', 3, 15);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('1641211499', 2, 4);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('269602057', 4, 1);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('269602057', 2, 3);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('269602057', 5, 10);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('269602057', 1, 16);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('27525363', 6, 16);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('27525363', 5, 10);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('27525363', 3, 5);

Insert into jeju_place_user_theme(place_id, user_no, theme_no) 
values('27525363', 4, 9);
