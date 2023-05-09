job('NodeJS Docker Job example') {
    //properties {
    //	githubProjectUrl('https://github.com/JulianPinzaru/docker-demo.git')
    //}
    scm {
        git('https://github.com/JulianPinzaru/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('pinzaru.iulian.igor@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('NodeJS 7') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('iulianpinzaru/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            // dockerRegistryURL('https://index.docker.io/v2/')
            registryCredentials('docker_hub_registry')
            noCache(true)
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
