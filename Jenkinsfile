pipeline{
    agent any
    tools {
        gradle 'gradle'
    }
    stages{
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Prepare'){
            steps {
                sh 'gradle clean'
            }
        }
        stage('Replace Prod Properties') {
            steps {
                withCredentials([file(credentialsId: 'shop', variable: 'shop')]) {
                    script {
                        sh 'pwd'
                        sh 'chmod -R 755 ./scr/main/resources'
                        sh 'cp $shop ./src/main/resources/application-prod.yml'
                    }
                }
            }
        }
        stage("old container remove") {
            steps {
                sh 'docker stop shop || true'
                sh 'docker rm shop || true'
                sh 'docker rmi shop || true'

                echo 'remove!!!'
            }
        }

        stage('Build') {
            steps {
                sh '''
                    pwd
                    chmod +x gradlew
                    ./gradlew build
                '''

                echo 'build!!!'
            }
        }

        stage('docker image build') {
            steps {
                sh '''
                   docker build -t shop .
                '''

                echo 'Docker build!!!'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    pwd
                    docker run -d --name shop -p 9090:9090 shop
                '''

                echo 'Deploy!!!'
            }
        }
    }
}
