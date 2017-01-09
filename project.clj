(defproject query-ut "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {}

  :dependencies [[org.clojure/clojure "1.8.0"]
                 [overtone "0.10.1"]
                 [midi.soundfont "0.1.0"]
                 [org.bitbucket.daveyarwood/fluid-r3 "0.1.1"]]

  :main ^:skip-aot main
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}

  :source-paths ["src"]
)
