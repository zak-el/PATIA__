(define (problem depotprob7615) (:domain Depot)
(:objects
	depot0 depot1 depot2 depot3 - Depot
	distributor0 distributor1 distributor2 distributor3 - Distributor
	truck0 truck1 truck2 truck3 - Truck
	pallet0 pallet1 pallet2 pallet3 pallet4 pallet5 pallet6 pallet7 pallet8 pallet9 - Pallet
	crate0 crate1 crate2 crate3 crate4 crate5 crate6 crate7 crate8 crate9 crate10 crate11 crate12 crate13 crate14 - Crate
	hoist0 hoist1 hoist2 hoist3 hoist4 hoist5 hoist6 hoist7 - Hoist)
(:init
	(at pallet0 depot0)
	(clear crate13)
	(at pallet1 depot1)
	(clear crate14)
	(at pallet2 depot2)
	(clear pallet2)
	(at pallet3 depot3)
	(clear crate5)
	(at pallet4 distributor0)
	(clear pallet4)
	(at pallet5 distributor1)
	(clear crate9)
	(at pallet6 distributor2)
	(clear crate8)
	(at pallet7 distributor3)
	(clear crate10)
	(at pallet8 depot1)
	(clear crate11)
	(at pallet9 depot2)
	(clear pallet9)
	(at truck0 distributor2)
	(at truck1 depot0)
	(at truck2 depot1)
	(at truck3 distributor1)
	(at hoist0 depot0)
	(available hoist0)
	(at hoist1 depot1)
	(available hoist1)
	(at hoist2 depot2)
	(available hoist2)
	(at hoist3 depot3)
	(available hoist3)
	(at hoist4 distributor0)
	(available hoist4)
	(at hoist5 distributor1)
	(available hoist5)
	(at hoist6 distributor2)
	(available hoist6)
	(at hoist7 distributor3)
	(available hoist7)
	(at crate0 distributor3)
	(on crate0 pallet7)
	(at crate1 distributor1)
	(on crate1 pallet5)
	(at crate2 depot3)
	(on crate2 pallet3)
	(at crate3 depot0)
	(on crate3 pallet0)
	(at crate4 depot0)
	(on crate4 crate3)
	(at crate5 depot3)
	(on crate5 crate2)
	(at crate6 depot1)
	(on crate6 pallet1)
	(at crate7 distributor2)
	(on crate7 pallet6)
	(at crate8 distributor2)
	(on crate8 crate7)
	(at crate9 distributor1)
	(on crate9 crate1)
	(at crate10 distributor3)
	(on crate10 crate0)
	(at crate11 depot1)
	(on crate11 pallet8)
	(at crate12 depot1)
	(on crate12 crate6)
	(at crate13 depot0)
	(on crate13 crate4)
	(at crate14 depot1)
	(on crate14 crate12)
)

(:goal (and
		(on crate0 pallet3)
		(on crate1 crate11)
		(on crate2 pallet6)
		(on crate3 crate0)
		(on crate4 crate5)
		(on crate5 crate14)
		(on crate6 pallet4)
		(on crate7 pallet2)
		(on crate8 pallet7)
		(on crate9 crate8)
		(on crate11 pallet5)
		(on crate12 crate6)
		(on crate13 crate2)
		(on crate14 pallet1)
	)
))
