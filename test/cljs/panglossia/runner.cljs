(ns panglossia.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [panglossia.core-test]))

(doo-tests 'panglossia.core-test)
