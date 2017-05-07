package com.itexus.copylocale

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectories
import org.gradle.api.tasks.TaskAction

class CopyTask extends DefaultTask {

    @InputFiles
    List<File> sourceDirs;

    @OutputDirectories
    List<File> destDirs;

    @TaskAction
    def copy() {
        sourceDirs.eachWithIndex { sourceDir, index ->
            destDirs.each { destDir ->
                project.copy {
                    from sourceDir
                    into destDir
                    rename {
                        it.replace(".", "-$index.")
                    }
                }
            }
        }
    }
}