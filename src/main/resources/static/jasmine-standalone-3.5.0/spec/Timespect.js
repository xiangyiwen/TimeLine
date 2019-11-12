describe("Time", function() {
    var date = new Date();
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    var hour = date.getHours();
    var minute = date.getMinutes();

   describe("##1", function() {
        var time = new Time();
        var fulltime = year+"-"+month+"-"+day+" "+hour+":"+minute;
        it("should be 刚刚", function() {
            var a = time.timeChange(fulltime);
            expect(a).toEqual("刚刚");
        });
   });

    describe("##2", function() {
        var time = new Time();
        var difftime = (new Date()).getTime() - 30*60*1000;
        var newtime = new Date(difftime);
        var year2 = newtime.getFullYear();
        var month2 = newtime.getMonth()+1;
        var day2 = newtime.getDate();
        var hour2 = newtime.getHours();
        var minute2 = newtime.getMinutes();

        var time2 = year2+"-"+month2+"-"+day2+" "+hour2+":"+minute2;
        it("should be 30分钟前", function() {
            var a = time.timeChange(time2);
            expect(a).toBe("30分钟前");
        });
    });

    //demonstrates use of expected exceptions
    describe("##3", function() {
        var time = new Time();
        var date = "2019-11-12 11:30";
        it("should be 11:30", function() {
            var a = time.timeChange(date);
            expect(a).toBe("11:30");
        });
    });

    describe("##4", function() {
        var time = new Time();
        var date = "2019-11-11 11:30";
        it("should be 昨天 11:30", function() {
            var a = time.timeChange(date);
            expect(a).toBe("昨天11:30");
        });
    });

    describe("##5", function() {
        var time = new Time();
        var date = "2019-11-8 11:30";
        it("should be 11-8 11:30", function() {
            var a = time.timeChange(date);
            expect(a).toBe("11-8 11:30");
        });
    });
});
