<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/8/21
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chart</title>
    <script type="text/javascript" src="js/jquery.js"></script>
    <script type="text/javascript" src="js/json2.js"></script>
    <script type="text/javascript" src="js/esl.js"></script>
    <script type="text/javascript">

        // 路径配置
        require.config({
            paths: {
                'echarts': 'js/echarts.min',
            }
        });


        $.ajaxSettings.async = false;
        $.getJSON('search_CCFNum', function (data) {
            var useridCCF = [];
            var usernameCCF = [];
            var countA = [];
            var countB = [];
            var countC = [];
            var countN = [];
            usernameCCF[0] = data.list[0].username;
            useridCCF[0] = data.list[0].user_id;
            for (var i = 0, j = 0; i < data.list.length; i++) {
                if (useridCCF[j] != data.list[i].user_id) {
                    useridCCF[j + 1] = data.list[i].user_id;
                    usernameCCF[j + 1] = data.list[i].username;
                    j++;
                    if (data.list[i].CCF_status == "A") {
                        countA[j] = data.list[i].num;
                        countB[j] = 0;
                        countC[j] = 0;
                        countN[j] = 0;
                    }
                    else if (data.list[i].CCF_status == "B") {
                        countA[j] = 0;
                        countB[j] = data.list[i].num;
                        countC[j] = 0;
                        countN[j] = 0;
                    }

                    else if (data.list[i].CCF_status == "C") {
                        countA[j] = 0;
                        countB[j] = 0;
                        countC[j] = data.list[i].num;
                        countN[j] = 0;
                    }

                    else {
                        countA[j] = 0;
                        countB[j] = 0;
                        countC[j] = 0;
                        countN[j] = data.list[i].num;
                    }
                }
                else {
                    if (data.list[i].CCF_status == "A")
                        countA[j] = data.list[i].num;
                    else if (data.list[i].CCF_status == "B")
                        countB[j] = data.list[i].num;
                    else if (data.list[i].CCF_status == "C")
                        countC[j] = data.list[i].num;
                    else
                        countN[j] = data.list[i].num;
                }
            }
            optionCCF = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['CCF A类', 'CCF B类', 'CCF C类', '非CCF']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: usernameCCF
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'CCF A类',
                        type: 'bar',
                        data: countA
                    },
                    {
                        name: 'CCF B类',
                        type: 'bar',
                        data: countB
                    },
                    {
                        name: 'CCF C类',
                        type: 'bar',
                        data: countC
                    },
                    {
                        name: '非CCF',
                        type: 'bar',
                        data: countN
                    }
                ]
            };
        });
        $.getJSON('search_categoryNum', function (data) {
            var useridcategory = [];
            var usernamecategory = [];
            var categorySEI = [];
            var categoryEI = [];
            var categoryN = [];
            usernamecategory[0] = data.list[0].username;
            useridcategory[0] = data.list[0].user_id;
            for (var i = 0, j = 0; i < data.list.length; i++) {
                if (useridcategory[j] != data.list[i].user_id) {
                    useridcategory[j + 1] = data.list[i].user_id;
                    usernamecategory[j + 1] = data.list[i].username;
                    j++;
                    if (data.list[i].category == "SEI") {
                        categorySEI[j] = data.list[i].num;
                        categoryEI[j] = 0;
                        categoryN[j] = 0;
                    }
                    else if (data.list[i].category == "EI") {
                        categorySEI[j] = 0;
                        categoryEI[j] = data.list[i].num;
                        categoryN[j] = 0;
                    }
                    else  {
                        categorySEI[j] = 0;
                        categoryEI[j] = 0;
                        categoryN[j] = data.list[i].num;
                    }

                }
                else {
                    if (data.list[i].category == "SEI")
                        categorySEI[j] = data.list[i].num;
                    else if (data.list[i].category == "EI")
                        categoryEI[j] = data.list[i].num;
                    else
                        categoryN[j] = data.list[i].num;
                }
            }
            optionCategory = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['SEI类', 'EI类', '非SEI/EI']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: usernamecategory
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'SEI类',
                        type: 'bar',
                        data: categorySEI
                    },
                    {
                        name: 'EI类',
                        type: 'bar',
                        data: categoryEI
                    },
                    {
                        name: '非SEI/EI',
                        type: 'bar',
                        data: categoryN
                    }
                ]
            };
        });
        $.getJSON('search_GroupNumWithCCF', function (data) {
            var groupidGCCF = [];
            var groupnameGCCF = [];
            var countA = [];
            var countB = [];
            var countC = [];
            var countN = [];
            groupidGCCF[0] = data.list[0].group_id;
            groupnameGCCF[0] = data.list[0].group_name;
            for (var i = 0, j = 0; i < data.list.length; i++) {
                if (groupidGCCF[j] != data.list[i].group_id) {
                    groupidGCCF[j + 1] = data.list[i].group_id;
                    groupnameGCCF[j + 1] = data.list[i].group_name;
                    j++;
                    if (data.list[i].CCF_status == "A") {
                        countA[j] = data.list[i].num;
                        countB[j] = 0;
                        countC[j] = 0;
                        countN[j] = 0;
                    }
                    else if (data.list[i].CCF_status == "B") {
                        countA[j] = 0;
                        countB[j] = data.list[i].num;
                        countC[j] = 0;
                        countN[j] = 0;
                    }

                    else if (data.list[i].CCF_status == "C") {
                        countA[j] = 0;
                        countB[j] = 0;
                        countC[j] = data.list[i].num;
                        countN[j] = 0;
                    }

                    else {
                        countA[j] = 0;
                        countB[j] = 0;
                        countC[j] = 0;
                        countN[j] = data.list[i].num;
                    }
                }
                else {
                    if (data.list[i].CCF_status == "A")
                        countA[j] = data.list[i].num;
                    else if (data.list[i].CCF_status == "B")
                        countB[j] = data.list[i].num;
                    else if (data.list[i].CCF_status == "C")
                        countC[j] = data.list[i].num;
                    else
                        countN[j] = data.list[i].num;

                }
            }
            optionGCCF = {
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                legend: {
                    data: ['CCF A类', 'CCF B类', 'CCF C类', '非CCF']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [
                    {
                        type: 'category',
                        data: groupnameGCCF
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: [
                    {
                        name: 'CCF A类',
                        type: 'bar',
                        data: countA
                    },
                    {
                        name: 'CCF B类',
                        type: 'bar',
                        data: countB
                    },
                    {
                        name: 'CCF C类',
                        type: 'bar',
                        data: countC
                    },
                    {
                        name: '非CCF',
                        type: 'bar',
                        data: countN
                    }
                ]
            };
        });


        require([
                    'echarts',
                ],
                function (ec) {
                    //CCFNUM
                    var myChart = ec.init(document.getElementById('CCFNum'));
                    myChart.setOption(optionCCF);
                    var myChart = ec.init(document.getElementById('YearNum'));
                    myChart.setOption(optionCategory);
                    var myChart = ec.init(document.getElementById('GCCFNum'));
                    myChart.setOption(optionGCCF);
                }
        )
    </script>
</head>
<body>
<div id="CCFNum" style="height:400px"></div>
<div id="YearNum" style="height:400px"></div>
<div id="GCCFNum" style="height:400px"></div>
</body>
</html>
