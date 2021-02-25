(define (domain robot-strips)
  (:predicates (at ?r) (connected ?r1 ?r2))
  (:action move
   :parameters (?from ?to)
   :precondition (and (at ?from)
                      (connected ?from ?to))
   :effect (and (not (at ?from))
                (at ?to))
)