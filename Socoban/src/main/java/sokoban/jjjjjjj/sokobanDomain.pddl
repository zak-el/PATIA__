(define (domain sokobanDomain)

  (:requirements :strips :typing)

  (:types location direction - object )

 (:predicates 	(is-free ?loc - location )
                (box-at ?loc -location)
                (player-at ?loc -location )
                (next-to ?from ?to -location ?dir -direction)
)

 (:action move
    :parameters (?from ?to - location ?dir - direction)
    :precondition (and (player-at ?from ) (is-free ?to) (next-to ?from ?to ?dir))
    :effect (and (not ( player-at ?from  )) (player-at ?to ) (not(is-free ?to)) (is-free ?from))
  )

 (:action push
    :parameters (?from ?to ?next - location ?dir -direction)
    :precondition (and (player-at ?from ) (is-free ?next) (next-to ?from ?to ?dir) (box-at ?to) (next-to ?to ?next ?dir))
    :effect (and (is-free ?from) (not(player-at ?from)) (box-at ?next) (player-at ?to) (not(box-at ?to)) (not(is-free ?next))
  )
 )
)