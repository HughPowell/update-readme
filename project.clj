(defproject update-readme "0.1.0-SNAPSHOT"
  :description "Update the dependency version in the README.md file"
  :url "http://github.com/HughPowell/update-readme"
  :license {:name "Mozilla Public License v2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :repositories [["releases" {:url "https://clojars.org/repo/"
                              :username :env
                              :password :env
                              :sign-releases false}]]
  :release-tasks [["vcs" "assert-committed"]
                  ["change" "version" "leiningen.release/bump-version" "release"]
                  #_["update-readme"]
                  ["vcs" "commit"]
                  ["vcs" "tag" "--no-sign"]
                  ["deploy"]
                  ["change" "version" "leiningen.release/bump-version"]
                  #_["update-readme"]
                  ["vcs" "commit"]
                  ["vcs" "push"]]
  :eval-in-leiningen true)
