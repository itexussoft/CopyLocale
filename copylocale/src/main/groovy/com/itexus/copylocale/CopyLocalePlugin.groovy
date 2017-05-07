package com.itexus.copylocale

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.Task

class CopyLocalePlugin implements Plugin<Project> {

    void apply(Project project) {
        project.extensions.create("copyLocale", CopyLocalePluginExtension)

        project.afterEvaluate {
            if (project.copyLocale.from == null && project.copyLocale.into == null) {
                return
            }

            project.android.applicationVariants.all { variant ->
                String sourceLocale = project.copyLocale.from

                List<File> sourceResDirs = []
                variant.sourceSets.each { sourceSet ->
                    sourceSet.res.srcDirs.each { resDir ->
                        sourceResDirs += project.file("$resDir/values-$sourceLocale")
                    }
                }

                File generatedResDir = project.file("$project.buildDir/generated/copyLocale/$flavorName/$buildType.name/$sourceLocale")
                List<File> destCopyResDirs = []
                project.copyLocale.into.each {
                    destCopyResDirs += project.file("$generatedResDir.absolutePath/values-$it/")
                }

                Task copyTask = project.task(
                        "copy${sourceLocale.capitalize()}Locale" +
                                "For${flavorName.capitalize()}${buildType.name.capitalize()}",
                        type: CopyTask
                ) {
                    sourceDirs = sourceResDirs
                    destDirs = destCopyResDirs
                }

                variant.registerResGeneratingTask(copyTask, generatedResDir)
            }
        }
    }
}