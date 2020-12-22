#!groovy
pipeline {
  agent {
    docker { image 'maven:3.6-jdk-11-openj9' }
  }
  environment {
      BRANCH_NAME=env.GIT_BRANCH.replace("origin/", "")
  }

  stages {
    stage('Build and Publish Pacts') {
      steps {
        script {
            sh 'mvn clean verify pact:publish -Dpact.consumer.version=${GIT_COMMIT} -Dpact.tag=CONTRACT-TEST'
          }
        }
      }
    }

  stages {
    stage('Build') {
      steps {
        script {
            sh "chmod +x mvnw"
            sh './mvnw clean verify'
        }
      }
    }
    stage('Publish Pacts') {
      steps {
          sh 'mvn pact:publish -Dpact.consumer.version=${GIT_COMMIT} -Dpact.tag=${BRANCH_NAME}'
      }
    }
    stage('Check Pact Verifications') {
      steps {
        sh 'curl -LO https://github.com/pact-foundation/pact-ruby-standalone/releases/download/v1.82.3/pact-1.82.3-linux-x86_64.tar.gz'
        sh 'tar xzf pact-1.82.3-linux-x86_64.tar.gz'
        dir('pact/bin') {
          sh "./pact-broker can-i-deploy --retry-while-unknown=12 --retry-interval=10 -a AccountBalanceConsumer -b http://pact_broker -e ${GIT_COMMIT}"
        }
      }
    }
    stage('Deploy') {
      when {
        branch 'master'
      }
      steps {
        echo 'Deploying to prod now...'
      }
    }
  }

}