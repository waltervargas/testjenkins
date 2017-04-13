def git_url = GIT_URL

freeStyleJob('deploy-infrastructure') {
  logRotator(-1,100)
  parameters {
    choiceParam('ENV', ['dev', 'qa', 'prod'], '"Environment to deploy the CF stacks to.')
    choiceParam("STACK_TYPE",['iam', 'vpc', 'vpn'],"Select the stack to deploy")
  }
  scm {
    git {
      branch('master')
      remote {
        github(git_url, 'ssh')
        //credentials('Github-Credentials')
      }
    }
  }
  steps {
    shell(readFileFromWorkspace('jenkins-jobs/deploy-infrastructure/scripts/hello.sh'))
  }
}
