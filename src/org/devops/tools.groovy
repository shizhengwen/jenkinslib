package org.devops

//// 打印内容
//def PrintMsg(content){
//    println(content)
//}

// 格式化输出
def PrintMsg(value, color){
    color = ['red': "\033[40;31m >>>>>>>>>>${value}<<<<<<<<033[0m",
             'blue': "\033[47;34m ${value}>>>>>>>>\033[m",
             'green': "[1;32m>>>>>>>>>${value}>>>>>>>[m",
             'green1': "\033[40;32m>>>>>>>>>>>${value}<<<<<<<<< \033[om"]
    ansiColor('xterm'){
        println(colors[color])
    }
}

