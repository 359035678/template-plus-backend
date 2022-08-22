#! /bin/bash

# Copyright 2019-2029 geekidea(https://github.com/geekidea)
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

#======================================================================
# 项目启动shell脚本
# config目录: 配置文件目录
# logs目录: 项目运行日志目录
# logs/spring-boot-plus_startup.log: 记录启动日志
# logs/back目录: 项目运行日志备份目录
# nohup后台运行
#
# author: geekidea
# date: 2018-12-2
#======================================================================
# 项目名称
APPLICATION=${project.build.finalName}

# 项目启动jar包名称
APPLICATION_JAR="${APPLICATION}.jar"

echo ${APPLICATION_JAR}

# bin目录绝对路径
BIN_PATH=$(cd `dirname $0`; pwd)


# 进入bin目录
cd `dirname $0`
# 返回到上一级项目根目录路径
cd ..
# 打印项目根目录绝对路径
# `pwd` 执行系统命令并获得结果
BASE_PATH=`pwd`

# 外部配置文件绝对目录,如果是目录需要/结尾，也可以直接指定文件
# 如果指定的是目录,spring则会读取目录中的所有配置文件
CONFIG_DIR=${BASE_PATH}"/config/"

# 项目日志输出绝对路径
LOG_DIR=${BASE_PATH}"/logs"
# 如果logs文件夹不存在,则创建文件夹
if [ ! -d "${LOG_DIR}" ]; then
  mkdir "${LOG_DIR}"
fi

#==========================================================================================
# JVM Configuration
# -Xmx1g:设置JVM最大可用内存为1G。
# -Xms1g:设置JVM初始内存1g。此值可以设置与-Xmx相同,以避免每次垃圾回收完成后JVM重新分配内存
# -Xmn512m:设置年轻代大小为512m。整个JVM内存大小=年轻代大小 + 年老代大小 + 持久代大小。
#          持久代一般固定大小为64m,所以增大年轻代,将会减小年老代大小。此值对系统性能影响较大,Sun官方推荐配置为整个堆的3/8
# -XX:MetaspaceSize=64m:存储class的内存大小,该值越大触发Metaspace GC的时机就越晚
# -XX:MaxMetaspaceSize=256m:限制Metaspace增长的上限，防止因为某些情况导致Metaspace无限的使用本地内存，影响到其他程序
# -XX:-OmitStackTraceInFastThrow:解决重复异常不打印堆栈信息问题
#==========================================================================================
JAVA_OPT="-server -Xms2g -Xmx2g -Xmn1g -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=256m"
JAVA_OPT="${JAVA_OPT} -XX:-OmitStackTraceInFastThrow"
#======================================================================
# 执行启动命令：后台启动项目,并将日志输出到项目根目录下的logs文件夹下
#======================================================================


nohup \
  java ${JAVA_OPT} \
  -Dspring.config.location=${CONFIG_DIR} \
  -jar ${BASE_PATH}/lib/${APPLICATION_JAR}  > /dev/null 2>&1 &


# 追加新的空白行到日志文件
echo "" >> $BASE_PATH/logs/${projectName}.log
