(defproject query-ut "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.apache.commons/commons-lang3 "3.3.2"]]

  :main ^:skip-aot main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}

  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"])

