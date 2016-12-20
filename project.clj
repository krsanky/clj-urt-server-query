(defproject query-ut "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.commons/commons-lang3 "3.3.2"]
                 [aleph "0.4.1"]]
  :main ^:skip-aot query-ut.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"])
