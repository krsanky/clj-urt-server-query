(ns org.oldcode.noise
  (:require [midi.soundfont :refer (load-all-instruments! midi-test load-patch)]
            [midi.soundfont.fluid-r3 :as fluid-r3])
  (:import (javax.sound.midi MidiSystem)))

(defn test5 []
  (def synth (MidiSystem/getSynthesizer))
  (load-all-instruments! synth fluid-r3/sf2)

  ;;(midi-test synth) ; you should hear a nice-sounding piano

  (load-patch synth 30)
  (midi-test synth) ; you should hear a gnarly distorted guitar!
  )
