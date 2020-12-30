<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<style>
/* #aplayer{ */
/* width: 600px; */
/* height:200px; */
/* position: absolute; */
/* left:10%; */
/* top:80%; */
/* margin-left:-100px; */
/* margin-top:-100px; */
/* } */
#d1 {
	width:1000px;
	position: relative;
	top: 100px;
	left: 780px;
	color: black;
}
#d1 h1{
position: absolute;
left: 100px;
}

#login{
position: absolute;
left: 850px;
top: 400px;
}
#tlogin td{
color: white;
font-size: 20px;
}
#tlogin td img{
width: 100px;
height: 100px;
}
#hitokoto{
color:white;
font-size: 25px;
position: absolute;
top: 100px;
}
body {
	background-image: url("music/adventure-attraction-blurry-618545.jpg");
	background-size: 1920px 1080px;
}
</style>
<body>
	<link rel="stylesheet" href="music/APlayer.min.css">
	<div id="d1">
		<table>
		<tr>
		<td align="center"><h1>书城登陆</h1>
		</tr>
		<tr>
		<td><p id="hitokoto">:D 获取中...</p>
		</tr>
		</table>
	</div>

	<div id="login">
		<form method="post" action="user?opr=login">
			<table border="1" id="tlogin">
				<tr>
					<td colspan="2" align="center"><img alt="" src="music/LogoSample_ByTailorBrands.jpg">
				</tr>
				<tr>
					<td colspan="2" align="center">秦风戈书城欢迎您登陆！
				</tr>
				<tr>
					<td>用户名:
					<td><input type="text" name="userName">
				</tr>
				<tr>
					<td>密码:
					<td><input type="password" name="passwd">
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit" value="提交">&nbsp;&nbsp;&nbsp;<input type="reset" value="重置">
				</tr>
			</table>
		</form>
	<a href="register.jsp" style="color: white;">还没有账号？点击去注册，畅享十万好书！</a>
	</div>
	<div id="aplayer">
		<script src="music/APlayer.min.js"></script>
		<script type="text/javascript" src="music/yinghua.js"></script>
		<script type="text/javascript">
			const
			ap1 = new APlayer(
					{
						container : document.getElementById('aplayer'),
						fixed : true,
						audio : [
								{
									name : '盗将行',
									artist : '花粥',
									url : 'http://music.163.com/song/media/outer/url?id=574566207.mp3',
									cover : 'http://p1.music.126.net/a1U7azl9lHF_1vZdJ8YYmA==/109951163366440346.jpg?param=106x106',
									lrc : '[00:00.00] 作曲 : 花粥[00:01.00] 作词 : 姬霄[00:08.825] 编曲/混音：马雨阳[00:18.217] 劫过九重城关[00:21.184] 我座下马正酣[00:23.656] 看那轻飘飘的衣摆[00:26.975] 趁擦肩把裙掀[00:29.877] 踏遍三江六岸[00:33.210] 借刀光做船帆[00:35.945] 任露水浸透了短衫[00:39.393] 大盗睥睨四野[00:43.735] 枕风宿雪多年[00:47.194] 我与虎谋早餐[00:49.688] 拎着钓叟的鱼弦[00:53.357] 问卧龙几两钱[00:55.874] 蜀中大雨连绵[00:59.291] 关外横尸遍野[01:02.138] 你的笑像一条恶犬[01:05.303] 撞乱了我心弦[01:23.009] 谈花饮月赋闲[01:25.771] 这春宵艳阳天[01:28.275] 待到梦醒时分睁眼[01:31.770] 铁甲寒意凛冽[01:34.304] 夙愿只隔一箭[01:37.704] 故乡近似天边[01:40.608] 不知何人浅唱弄弦[01:44.009] 我彷徨不可前[01:51.313] 枕风宿雪多年[01:54.645] 我与虎谋早餐[01:57.337] 拎着钓叟的鱼弦[02:00.841] 问卧龙几两钱[02:03.517] 蜀中大雨连绵[02:07.049] 关外横尸遍野[02:09.771] 你的笑像一条恶犬[02:13.137] 撞乱我心弦[02:15.916] 烽烟万里如衔[02:19.290] 掷群雄下酒宴[02:22.002] 谢绝策勋十二转[02:25.539] 想为你窃玉簪[02:28.204] 入巷间吃汤面[02:31.690] 笑看窗边飞雪[02:34.340] 取腰间明珠弹山雀[02:37.721] 立枇杷于庭前[02:45.748] 入巷间吃汤面[02:49.694] 笑看窗边飞雪[02:53.209] 取腰间明珠弹山雀[02:57.041] 立枇杷于庭前',
									theme : '#ebd0c2'
								},
								{
									name : 'Lemon',
									artist : '米津玄師',
									url : 'http://music.163.com/song/media/outer/url?id=536622304.mp3',
									cover : 'http://p1.music.126.net/r0TgUXBEEmMG48KSsEa_mg==/109951163143657970.jpg?param=130y130',
									lrc : '[00:00.850]夢ならばどれほどよかったでしょう[00:06.650]未だにあなたのことを夢にみる[00:12.340]忘れた物を取りに帰るように[00:17.660]古びた思い出の埃を払う[00:25.820]戻らない幸せがあることを[00:31.580]最後にあなたが教えてくれた[00:36.980]言えずに隠してた昏い過去も[00:42.550]あなたがいなきゃ永遠に昏いまま[00:48.230]きっともうこれ以上 傷つくことなど[00:53.750]ありはしないとわかっている[00:58.660]あの日の悲しみさえ[01:01.410]あの日の苦しみさえ[01:04.150]そのすべてを愛してた あなたとともに[01:09.800]胸に残り離れない[01:12.730]苦いレモンの匂い[01:15.730]雨が降り止むまでは帰れない[01:21.240]今でもあなたはわたしの光[01:37.600]暗闇であなたの背をなぞった[01:42.980]その輪郭を鮮明に覚えている[01:48.800]受け止めきれないものと出会うたび[01:54.400]溢れてやまないのは涙だけ[01:59.990]何をしていたの[02:02.760]何を見ていたの[02:05.580]わたしの知らない横顔で[02:10.390]どこかであなたが今[02:13.380]わたしと同じ様な[02:15.950]涙にくれ 淋しさの中にいるなら[02:21.470]わたしのことなどどうか 忘れてください[02:27.490]そんなことを心から願うほどに[02:32.910]今でもあなたはわたしの光[02:41.600]自分が思うより 恋をしていたあなたに[02:52.410]あれから思うように 息ができない[03:03.360]あんなに側にいたのにまるで嘘みたい[03:14.140]とても忘れられないそれだけが確か[03:30.430]あの日の悲しみさえ[03:33.200]あの日の苦しみさえ[03:35.910]その全てを愛してたあなたと共に[03:41.320]胸に残り離れない[03:44.280]苦いレモンの匂い[03:47.730]雨が降り止むまでは帰れない[03:52.840]切り分けた果実の片方の様に[03:58.590]今でもあなたはわたしの光',
									theme : '#46718b'
								},
								{
									name : '往后余生',
									artist : '王贰浪',
									url : 'http://music.163.com/song/media/outer/url?id=571338279.mp3',
									cover : 'http://p1.music.126.net/hTiVEeQTUSsc-YGF3o6ItQ==/109951163337847600.jpg?param=130y130',
									lrc : '[00:00.00] 作曲 : 马良[00:01.00] 作词 : 马良[00:09.42][00:17.07]在没风的地方找太阳[00:23.64]在你冷的地方做暖阳[00:31.20]人事纷纷[00:34.69]你总是太天真[00:39.05]往后的余生[00:41.05]我只要你[00:44.74]往后余生[00:48.01]风雪是你[00:51.72]平淡是你[00:55.59]清贫也是你[00:59.27]荣华是你[01:02.78]心底温柔是你[01:07.36]目光所致[01:10.12]也是你[01:13.75][01:30.55]想带你去看晴空万里[01:37.63]想大声告诉你我为你着迷[01:46.09]往事匆匆[01:48.85]你总是会感动[01:52.85]往后的余生[01:54.98]我只要你[01:58.30]往后余生[02:01.90]风雪是你[02:05.83]春华是你[02:09.49]夏雨也是你[02:12.97]秋黄是你[02:16.76]四季冷暖是你[02:21.02]目光所致[02:23.82]也是你[02:29.22][02:57.41]往后余生[03:01.06]风雪是你[03:04.75]平淡是你[03:08.38]清贫也是你[03:12.13]荣华是你[03:15.42]心底温柔是你[03:19.91]目光所致[03:22.72]也是你[03:27.50]目光所致[03:31.19]也是你[03:33.30]制作人：王佳依 张燕峰[03:34.77]监制：姚政[03:36.12]编曲：张燕峰[03:37.74]伴唱：王贰浪[03:39.49]缩混：唐瑜[03:41.30]母带：唐瑜[03:42.66]录音室：KOl Musical Equipment Ltd[03:47.94]',
									theme : '#46718b'
								}, ]
					});
		</script>
	</div>
	
	<!-- 现代写法，推荐 -->
<!-- 兼容低版本浏览器 (包括 IE)，可移除 -->
<script src="https://cdn.jsdelivr.net/npm/bluebird@3/js/browser/bluebird.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/whatwg-fetch@2.0.3/fetch.min.js"></script>
<!--End-->
<script>
  fetch('https://v1.hitokoto.cn')
    .then(function (res){
      return res.json();
    })
    .then(function (data) {
      var hitokoto = document.getElementById('hitokoto');
      hitokoto.innerText = data.hitokoto; 
    })
</script>
</body>
</html>