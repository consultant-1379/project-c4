pipeline {
    agent {
        docker {
            image 'maven:3.8.1-adoptopenjdk-11'
            args '-v /root/.m2:/root/.m2'
        }
    }
    stages {
        stage('Build') {
            steps {
                sh 'pwd'
                dir('test-analysis-tool/project-rest-api'){
                    sh 'mvn -B -DskipTests clean package'
                }
                dir('test-analysis-tool/test-report-api'){
                    sh 'mvn -B -DskipTests clean package'
                }
            }
        }
        stage('Test') {
                    steps {
                        dir('test-analysis-tool'){
                            sh 'mvn test'
                        }
                    }
                    post {
                        always {
                            junit 'test-analysis-tool/test-report-api/target/surefire-reports/*.xml'
                        }
                    }
                }
    }
}