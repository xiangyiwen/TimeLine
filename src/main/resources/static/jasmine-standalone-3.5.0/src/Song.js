function Song() {
}

Song.prototype.persistFavoriteStatus = function(value) {
  // something complicated
  throw new Error("not yet implemented");
};

function Time() {

}

Time.prototype.isYestday = function(timeValue) {
    var time = (new Date(timeValue)).getTime();
    var date = new Date(); //当前时间
    var today = new Date(date.getFullYear(), date.getMonth(), date.getDate()).getTime(); //今天凌晨
    var yestday = new Date(today - 24 * 3600 * 1000).getTime();
    return time < today && yestday <= time;
};

//判断传入日期是否属于今年
Time.prototype.isYear =  function(timeValue) {
    var date = new Date();
    var takeNewYear = date.getFullYear(); //当前时间的年份
    var takeTimeValue = parseInt(timeValue.substr(0,4)); //传入时间的年份
    return takeTimeValue == takeNewYear;  //等于-1则不是今年
};

Time.prototype.timeChange = function(timeValue) {
    var time = (new Date(timeValue)).getTime();

    var timeNew = Date.parse(new Date()); //当前时间
    var timeDiffer = timeNew - time; //与当前时间误差
    var returnTime = '';
    if(timeDiffer <= 60000) { //一分钟内
        var returnTime = '刚刚';
    } else if(timeDiffer > 60000 && timeDiffer < 3600000) { //1小时内
        var returnTime = Math.floor(timeDiffer / 60000 )+ '分钟前';
    } else if(timeDiffer >= 3600000 && timeDiffer < 86400000 && !isYestday(timeValue)) { //今日
        var returnTime = timeValue.substr(11,5);
    } else if(timeDiffer > 3600000 && isYestday( timeValue)) { //昨天
        var returnTime = '昨天'+ timeValue.substr(11,5);
    } else if (timeDiffer > 86400000 && !isYestday(timeValue) && isYear (timeValue)){	//今年
        var returnTime = timeValue.substr(5,11);
    } else if (timeDiffer > 86400000 && !isYestday( timeValue) && !isYear (timeValue)) { //不属于今年
        var returnTime = timeValue.substr(0,16);
    }
    return returnTime;
};