#!/usr/bin/env sh

#
# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Add default JVM options here. You can also use JAVA_OPTS and GRADLE_OPTS to pass JVM options to this script.
DEFAULT_JVM_OPTS=""

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

# Use the maximum available, or set MAX_FD != -1 to use that value.
MAX_FD="maximum"

# OS specific support (must be 'true' or 'false').
cygwin=false
msys=false
darwin=false
nonstop=false
case "`uname`" in
  CYGWIN* )
    cygwin=true
    ;;
  Darwin* )
    darwin=true
    ;;
  MSYS* | MINGW* )
    msys=true
    ;;
  NONSTOP* )
    nonstop=true
    ;;
esac

CLASSPATH_SEPARATOR=:
if $cygwin || $msys; then
  CLASSPATH_SEPARATOR=";"
fi

# Attempt to set APP_HOME
# Resolve links: $0 may be a link
PRG="$0"
# Need this for relative symlinks.
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

# Add probing for SVN working copy in current directory.
DIRNAME_0="`dirname \"$0\"`"
if [ "$DIRNAME_0" = "." ] && [ -d ".svn" ]; then
    APP_HOME="."
fi

# Attempt to set CLASSPATH
# For Darwin, add tools.jar which is not on the system class path
if [ "$JAVA_HOME" != "" ] ; then
    if [ -r "$JAVA_HOME/lib/tools.jar" ] ; then
        TOOLS_JAR="$JAVA_HOME/lib/tools.jar"
    fi
fi
if [ -z "$TOOLS_JAR" ] && [ -n "$JDK_HOME" ] ; then
    if [ -r "$JDK_HOME/lib/tools.jar" ] ; then
        TOOLS_JAR="$JDK_HOME/lib/tools.jar"
    fi
fi
if [ -z "$TOOLS_JAR" ] && $darwin ; then
    # if this is a new JDK, use the embedded JLI
    if [ -r "/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Libraries/jli/libjli.dylib" ] ; then
        TOOLS_JAR="/System/Library/Frameworks/JavaVM.framework/Versions/CurrentJDK/Libraries/classes.jar"
    # if this is an old JDK, try to find tools.jar
    elif [ -r "/System/Library/Frameworks/JavaVM.framework/Versions/A/Libraries/tools.jar" ] ; then
        TOOLS_JAR="/System/Library/Frameworks/JavaVM.framework/Versions/A/Libraries/tools.jar"
    fi
fi

# Add the jar to the CLASSPATH
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
if [ "$TOOLS_JAR" != "" ] ; then
    CLASSPATH="$CLASSPATH${CLASSPATH_SEPARATOR}${TOOLS_JAR}"
fi

# Determine the Java command to use to start the JVM.
if [ "$JAVA_HOME" != "" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

# Increase the maximum file descriptors if we can.
if ! $cygwin && ! $darwin && ! $nonstop; then
    MAX_FD_LIMIT=`ulimit -H -n`
    if [ "$?" -eq 0 ] ; then
        if [ "$MAX_FD" = "maximum" -o "$MAX_FD" = "max" ] ; then
            # use the system max
            MAX_FD="$MAX_FD_LIMIT"
        fi
        ulimit -n $MAX_FD
        if [ "$?" -ne 0 ] ; then
            warn "Could not set maximum file descriptor limit: $MAX_FD"
        fi
    else
        warn "Could not query maximum file descriptor limit: $MAX_FD_LIMIT"
    fi
fi

# Escape application args
save () {
    for i do printf %s\\n "$i" | sed "s/'/'\\\\''/g;1s/^/'/;\$s/\$/' \\\\/" ; done
    echo " "
}
APP_ARGS_ESCAPED=`save "$@"`

# Collect all arguments for the java command, following the shell quoting and substitution rules
eval set -- "$DEFAULT_JVM_OPTS" "$JAVA_OPTS" "$GRADLE_OPTS" "\"-Dorg.gradle.appname=$APP_BASE_NAME\"" -classpath "\"$CLASSPATH\"" org.gradle.wrapper.GradleWrapperMain "$APP_ARGS_ESCAPED"

# Stop on error
set -e

# Start the JVM
exec "$JAVACMD" "$@"
