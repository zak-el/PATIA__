(define (problem sokobanProblem)
(:domain sokobanDomain)
(:objects
R -direction
L -direction
U -direction
D -direction
pos-0-0 - location
pos-0-1 - location
pos-0-2 - location
pos-0-3 - location
pos-0-4 - location
pos-0-5 - location
pos-0-6 - location
pos-0-7 - location
pos-0-8 - location
pos-1-0 - location
pos-1-1 - location
pos-1-2 - location
pos-1-3 - location
pos-1-4 - location
pos-1-5 - location
pos-1-6 - location
pos-1-7 - location
pos-1-8 - location
pos-2-0 - location
pos-2-1 - location
pos-2-2 - location
pos-2-3 - location
pos-2-4 - location
pos-2-5 - location
pos-2-6 - location
pos-2-7 - location
pos-2-8 - location
pos-3-0 - location
pos-3-1 - location
pos-3-2 - location
pos-3-3 - location
pos-3-4 - location
pos-3-5 - location
pos-3-6 - location
pos-3-7 - location
pos-3-8 - location
pos-4-0 - location
pos-4-1 - location
pos-4-2 - location
pos-4-3 - location
pos-4-4 - location
pos-4-5 - location
pos-4-6 - location
pos-4-7 - location
pos-4-8 - location
pos-5-0 - location
pos-5-1 - location
pos-5-2 - location
pos-5-3 - location
pos-5-4 - location
pos-5-5 - location
pos-5-6 - location
pos-5-7 - location
pos-5-8 - location
)
(:init
(next-to pos-0-0 pos-0-1 R)
(next-to pos-0-0 pos-1-0 D)
(next-to pos-0-1 pos-0-2 R)
(next-to pos-0-1 pos-1-1 D)
(next-to pos-0-1 pos-0-0 L)
(next-to pos-0-2 pos-0-3 R)
(next-to pos-0-2 pos-1-2 D)
(next-to pos-0-2 pos-0-1 L)
(next-to pos-0-3 pos-0-4 R)
(next-to pos-0-3 pos-1-3 D)
(next-to pos-0-3 pos-0-2 L)
(next-to pos-0-4 pos-0-5 R)
(next-to pos-0-4 pos-1-4 D)
(next-to pos-0-4 pos-0-3 L)
(next-to pos-0-5 pos-0-6 R)
(next-to pos-0-5 pos-1-5 D)
(next-to pos-0-5 pos-0-4 L)
(next-to pos-0-6 pos-1-6 D)
(next-to pos-0-6 pos-0-5 L)
(next-to pos-1-0 pos-1-1 R)
(next-to pos-1-0 pos-2-0 D)
(next-to pos-1-0 pos-0-0 U)
(next-to pos-1-1 pos-1-2 R)
(next-to pos-1-1 pos-2-1 D)
(next-to pos-1-1 pos-1-0 L)
(next-to pos-1-1 pos-0-1 U)
(is-free pos-1-1)
(next-to pos-1-2 pos-1-3 R)
(next-to pos-1-2 pos-2-2 D)
(next-to pos-1-2 pos-1-1 L)
(next-to pos-1-2 pos-0-2 U)
(is-free pos-1-2)
(next-to pos-1-3 pos-1-4 R)
(next-to pos-1-3 pos-2-3 D)
(next-to pos-1-3 pos-1-2 L)
(next-to pos-1-3 pos-0-3 U)
(next-to pos-1-4 pos-1-5 R)
(next-to pos-1-4 pos-2-4 D)
(next-to pos-1-4 pos-1-3 L)
(next-to pos-1-4 pos-0-4 U)
(is-free pos-1-4)
(next-to pos-1-5 pos-1-6 R)
(next-to pos-1-5 pos-2-5 D)
(next-to pos-1-5 pos-1-4 L)
(next-to pos-1-5 pos-0-5 U)
(is-free pos-1-5)
(next-to pos-1-6 pos-2-6 D)
(next-to pos-1-6 pos-1-5 L)
(next-to pos-1-6 pos-0-6 U)
(next-to pos-2-0 pos-2-1 R)
(next-to pos-2-0 pos-3-0 D)
(next-to pos-2-0 pos-1-0 U)
(next-to pos-2-1 pos-2-2 R)
(next-to pos-2-1 pos-3-1 D)
(next-to pos-2-1 pos-2-0 L)
(next-to pos-2-1 pos-1-1 U)
(is-free pos-2-1)
(next-to pos-2-2 pos-2-3 R)
(next-to pos-2-2 pos-3-2 D)
(next-to pos-2-2 pos-2-1 L)
(next-to pos-2-2 pos-1-2 U)
(is-free pos-2-2)
(next-to pos-2-3 pos-2-4 R)
(next-to pos-2-3 pos-3-3 D)
(next-to pos-2-3 pos-2-2 L)
(next-to pos-2-3 pos-1-3 U)
(box-at pos-2-3)
(next-to pos-2-4 pos-2-5 R)
(next-to pos-2-4 pos-3-4 D)
(next-to pos-2-4 pos-2-3 L)
(next-to pos-2-4 pos-1-4 U)
(is-free pos-2-4)
(next-to pos-2-5 pos-2-6 R)
(next-to pos-2-5 pos-3-5 D)
(next-to pos-2-5 pos-2-4 L)
(next-to pos-2-5 pos-1-5 U)
(is-free pos-2-5)
(next-to pos-2-6 pos-2-7 R)
(next-to pos-2-6 pos-3-6 D)
(next-to pos-2-6 pos-2-5 L)
(next-to pos-2-6 pos-1-6 U)
(next-to pos-2-7 pos-3-7 D)
(next-to pos-2-7 pos-2-6 L)
(next-to pos-2-7 pos-1-7 U)
(next-to pos-3-0 pos-3-1 R)
(next-to pos-3-0 pos-4-0 D)
(next-to pos-3-0 pos-2-0 U)
(next-to pos-3-1 pos-3-2 R)
(next-to pos-3-1 pos-4-1 D)
(next-to pos-3-1 pos-3-0 L)
(next-to pos-3-1 pos-2-1 U)
(is-free pos-3-1)
(next-to pos-3-2 pos-3-3 R)
(next-to pos-3-2 pos-4-2 D)
(next-to pos-3-2 pos-3-1 L)
(next-to pos-3-2 pos-2-2 U)
(player-at pos-3-2)
(next-to pos-3-3 pos-3-4 R)
(next-to pos-3-3 pos-4-3 D)
(next-to pos-3-3 pos-3-2 L)
(next-to pos-3-3 pos-2-3 U)
(next-to pos-3-4 pos-3-5 R)
(next-to pos-3-4 pos-4-4 D)
(next-to pos-3-4 pos-3-3 L)
(next-to pos-3-4 pos-2-4 U)
(box-at pos-3-4)
(next-to pos-3-5 pos-3-6 R)
(next-to pos-3-5 pos-4-5 D)
(next-to pos-3-5 pos-3-4 L)
(next-to pos-3-5 pos-2-5 U)
(box-at pos-3-5)
(next-to pos-3-6 pos-3-7 R)
(next-to pos-3-6 pos-4-6 D)
(next-to pos-3-6 pos-3-5 L)
(next-to pos-3-6 pos-2-6 U)
(is-free pos-3-6)
(next-to pos-3-7 pos-3-8 R)
(next-to pos-3-7 pos-4-7 D)
(next-to pos-3-7 pos-3-6 L)
(next-to pos-3-7 pos-2-7 U)
(next-to pos-3-8 pos-4-8 D)
(next-to pos-3-8 pos-3-7 L)
(next-to pos-3-8 pos-2-8 U)
(next-to pos-4-0 pos-4-1 R)
(next-to pos-4-0 pos-5-0 D)
(next-to pos-4-0 pos-3-0 U)
(next-to pos-4-1 pos-4-2 R)
(next-to pos-4-1 pos-5-1 D)
(next-to pos-4-1 pos-4-0 L)
(next-to pos-4-1 pos-3-1 U)
(is-free pos-4-1)
(next-to pos-4-2 pos-4-3 R)
(next-to pos-4-2 pos-5-2 D)
(next-to pos-4-2 pos-4-1 L)
(next-to pos-4-2 pos-3-2 U)
(is-free pos-4-2)
(next-to pos-4-3 pos-4-4 R)
(next-to pos-4-3 pos-5-3 D)
(next-to pos-4-3 pos-4-2 L)
(next-to pos-4-3 pos-3-3 U)
(box-at pos-4-3)
(next-to pos-4-4 pos-4-5 R)
(next-to pos-4-4 pos-5-4 D)
(next-to pos-4-4 pos-4-3 L)
(next-to pos-4-4 pos-3-4 U)
(is-free pos-4-4)
(next-to pos-4-5 pos-4-6 R)
(next-to pos-4-5 pos-5-5 D)
(next-to pos-4-5 pos-4-4 L)
(next-to pos-4-5 pos-3-5 U)
(is-free pos-4-5)
(next-to pos-4-6 pos-4-7 R)
(next-to pos-4-6 pos-5-6 D)
(next-to pos-4-6 pos-4-5 L)
(next-to pos-4-6 pos-3-6 U)
(is-free pos-4-6)
(next-to pos-4-7 pos-4-8 R)
(next-to pos-4-7 pos-5-7 D)
(next-to pos-4-7 pos-4-6 L)
(next-to pos-4-7 pos-3-7 U)
(is-free pos-4-7)
(next-to pos-4-8 pos-5-8 D)
(next-to pos-4-8 pos-4-7 L)
(next-to pos-4-8 pos-3-8 U)
(next-to pos-5-0 pos-5-1 R)
(next-to pos-5-0 pos-4-0 U)
(next-to pos-5-1 pos-5-2 R)
(next-to pos-5-1 pos-5-0 L)
(next-to pos-5-1 pos-4-1 U)
(is-free pos-5-1)
(next-to pos-5-2 pos-5-3 R)
(next-to pos-5-2 pos-5-1 L)
(next-to pos-5-2 pos-4-2 U)
(is-free pos-5-2)
(next-to pos-5-3 pos-5-4 R)
(next-to pos-5-3 pos-5-2 L)
(next-to pos-5-3 pos-4-3 U)
(next-to pos-5-4 pos-5-5 R)
(next-to pos-5-4 pos-5-3 L)
(next-to pos-5-4 pos-4-4 U)
(next-to pos-5-5 pos-5-6 R)
(next-to pos-5-5 pos-5-4 L)
(next-to pos-5-5 pos-4-5 U)
(is-free pos-5-5)
(next-to pos-5-6 pos-5-7 R)
(next-to pos-5-6 pos-5-5 L)
(next-to pos-5-6 pos-4-6 U)
(is-free pos-5-6)
(next-to pos-5-7 pos-5-8 R)
(next-to pos-5-7 pos-5-6 L)
(next-to pos-5-7 pos-4-7 U)
(is-free pos-5-7)
(next-to pos-5-8 pos-5-7 L)
(next-to pos-5-8 pos-4-8 U)
)
(:goal (and
(box-at pos-2-3)
(box-at pos-3-1)
(box-at pos-3-2)
(box-at pos-4-3)
))
)