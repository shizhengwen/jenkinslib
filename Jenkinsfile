@Library('jenkinslib') _
def tools = new org.devops.tools()
String workspace = "/opt/jenkins/workspace"
hello()
pipeline {
    
	// 指定在哪个节点上运行
    agent { node{ label "master"	// 指定运行节点的标签或者名称
                 customWorkspace "${workspace}"	// 指定运行工作目录(可选)
        }
    }
	parameters { string(name: 'DEPLOY_ENV', defaultValue: 'staging', description: '') }
	// 指定运行时的选项
	options {
	    timestamps()	//日志打印时间
		skipDefaultCheckout()	// 删除隐式checkout scm语句
		disableConcurrentBuilds()	// 禁止并行
		timeout(time: 1, unit: 'HOURS')	// 流水线超时设置1h
	}
	
	// 指定阶段
	stages{
	    // 下载代码
	    stage("GetCode"){ //阶段名称
	        when { environment name: 'test', value: 'abcd' }
		    steps{ // 步骤
			    timeout(time:5, unit:"MINUTES"){
				    script{ // 填写运行代码
					    print("获取代码")
					    tools.PrintMsg("获取代码", "green")
					    print("${test}")
					    
					    //input id: 'Test', message: '我们是否要继续？', ok: '是', parameters: [choice(choices: ['a', 'b'], name: 'test1')]
					}
				}
			}
		}
		
		stage("01"){
		    failFast true
            parallel {
        		// 构建
        		stage("Build"){
        		    steps{
        			    timeout(time:20, unit:"MINUTES"){
        				    script{
        					    print("应用打包")
						    tools.PrintMsg("应用打包", "green")
        					    //mvnHome = tool "m2"
        					    //print(mvnHome)
        					    //sh "${mvnHome}/bin/mvn --version"
        					}
        				}
        			}
        		}
        		
        		// 代码扫描
        		stage("CodeScan"){
        		    steps{
        			    timeout(time:30, unit:"MINUTES"){
        				    script{
        					    print("代码扫描")
                      tools.PrintMsg("代码扫描", "green")
        					}
        				}
        			}
        		}
    		}
		}
	
	}
	
	// 构建后的操作
	post{
	
	    always{ // 不管失败和成功都会执行
		    script{
			    print("always")
			}
		}
		
		success{ // 成功了去执行
		    script{
			    //currentBuild 是一个全局的变量
			    currentBuild.description ="\n 构建成功|"
			}
		}
		
		failure{ // 失败了去执行
		    script{
			    currentBuild.description ="\n 构建失败|"
			}
		}
		
		aborted{ // 取消了去执行
		    script{
			    currentBuild.description ="\n 构建取消|"
			}
		}
	}



}
