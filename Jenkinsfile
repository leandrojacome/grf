pipeline {
    agent any

    environment {
        JAVA = '17'
        MAVEN = '3'
        HELM_GL_PID = '1635'
        BRANCH = env.BRANCH.replace('origin/', '')
    }

    stages {
        stage('Release') {
            when { environment name: 'BRANCH', value: 'master' }
            steps {
                withMaven(maven: MAVEN, jdk: JAVA) {
                    sh "mvn git-release:execute"
                }
            }
        }
        stage('Publish image') {
            steps {
                script {
                    VERSION = readMavenPom().getVersion()
                    IMAGE_TAG = "${VERSION}" + ((BRANCH != 'master' ? "-${BRANCH}" : ''))
                }
                withMaven(maven: MAVEN, jdk: JAVA) {
                    sh "mvn package dockerfile:build dockerfile:push -Ddockerfile.tag=${IMAGE_TAG}"
                }
            }
        }
        stage('Deploy') {
            steps {
                script {
                    ARTIFACT_ID = readMavenPom().getArtifactId()
                    KUBECONFIG = BRANCH == 'master' ? "${KUBECONFIG_PROD}" : "${KUBECONFIG_HML}"
                    HELM_RELEASE = "${ARTIFACT_ID}-${BRANCH}"
                    if (BRANCH == 'master') {
                        withCredentials([string(credentialsId: 'jenkins-gitlab-token', variable: 'JNKS_GL_TOKEN')]) {
                            sh "curl -s -H 'PRIVATE-TOKEN: ${JNKS_GL_TOKEN}' https://gitlab.poupex.com.br/api/v4/projects/${HELM_GL_PID}/repository/files/values-prod.yaml/raw?ref=master -o values.yaml"
                        }
                    }
                }
                timeout(time: 5, unit: 'MINUTES') {
                    sh """
						helm repo update --kubeconfig ${KUBECONFIG} && touch values.yaml \
						&& helm upgrade --install --namespace=${HELM_RELEASE} ${HELM_RELEASE} \
						helmcharts/${ARTIFACT_ID} -f values.yaml --reset-values --kubeconfig ${KUBECONFIG} \
						--set imageTag=${IMAGE_TAG},branch=${BRANCH},springProfilesActive=${env.SPRING_PROFILES_ACTIVE} \
						--wait --timeout 300s --create-namespace
					"""
                }
            }
        }
    }

    post {
        success {
            cleanWs(skipWhenFailed: true)
        }
    }
}
