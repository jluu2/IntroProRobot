public class RobotControl {
	private Robot r;

	public RobotControl(Robot r) {
		this.r = r;
	}

	private int h = 2; // Initial height of arm 1
	private int w = 1; // Initial width of arm 2
	private int d = 0; // Initial depth of arm 3

	public int clearenceCheck(int targetHt[], int barHeights[], int sourceHt) {
		int maxHt = 0;

		for (int i = 0; i < targetHt.length; i++) {
			if (targetHt[i] > maxHt) {
				maxHt = targetHt[i];
			}
		}

		for (int i = 0; i < barHeights.length; i++) {
			if (barHeights[i] > maxHt) {
				maxHt = barHeights[i];
			}
		}

		if (sourceHt > maxHt) {
			maxHt = sourceHt;
		}

		return (maxHt + 1);
	}

	public int clearenceCheck(int targetHt[], int barHeights[]) {
		int maxHt = 0;

		for (int i = 0; i < targetHt.length; i++) {
			if (targetHt[i] > maxHt) {
				maxHt = targetHt[i];
			}
		}

		for (int i = 0; i < barHeights.length; i++) {
			if (barHeights[i] > maxHt) {
				maxHt = barHeights[i];
			}
		}

		return (maxHt + 1);
	}

	public int maxBarHeights(int barHeights[]) {
		int maxBarHeights = 0;

		for (int i = 0; i < barHeights.length; i++) {
			if (barHeights[i] > maxBarHeights) {
				maxBarHeights = barHeights[i];
			}
		}

		return maxBarHeights;
	}

	public void raise() {
		while (d > 0) {
			r.raise();
			d--;
		}
	}

	public void upDown(int clearence) {
		if (h > clearence) {
			while (h > clearence) {
				r.down();
				h--;
			}
		} else {
			while (h < clearence) {
				r.up();
				h++;
			}
		}
	}

	public void extend() {
		while (w < 10) {
			r.extend();
			w++;
		}
	}

	public void lower(int sourceHt) {
		while (h - d > sourceHt + 1) {
			r.lower();
			d++;
		}
	}

	public void contract(int targetWidth) {
		while (w > targetWidth) {
			r.contract();
			w--;
		}
	}

	public void control(int barHeights[], int blockHeights[]) {

		// Internally the Robot object maintains the value for Robot height(h),
		// arm-width (w) and picker-depth (d).
		// These values are displayed for your convenience
		// These values are initialised as h=2 w=1 and d=0
		// When you call the methods up() or down() h will be changed
		// When you call the methods extend() or contract() w will be changed
		// When you call the methods lower() or raise() d will be changed

		// sample code to get you started
		// Try running this program with obstacle 555555 and blocks of height
		// 123 (default)
		// It will work move topmost block part of the way
		// You are free to introduce any other variables

		// Calculate the Source Height
		int sourceHt = 0; // For Parts A and B 1+2+3 = 6
		for (int i = 0; i < blockHeights.length; i++) {
			sourceHt = sourceHt + blockHeights[i];
		}
		// System.out.println("sourceHt" + sourceHt);

		// Set BlockIndex to the Top Most Block
		int blockIndex = blockHeights.length - 1;

		// Initialize Target Heights Array
		int targetHt[] = new int[3];
		for (int i = 0; i < targetHt.length; i++) {
			targetHt[i] = 0;
		}

		// this causes unnecessary moves you should be set considering the
		// heights of bars, source, targets and current block
		// Initialize Clearance
		int clearence = 10;

		// Currently the topmost block is of height 3
		// Initialize Block Height
		int blockHt = 3;

		// As the topmost block is of height 3 it should be dropped in column 3
		// Initialize Target Width
		int targetWidth = 3;

		/*
		 * // Move Up or Down according to the Highest Obstacle clearence =
		 * clearenceCheck(targetHt, barHeights, sourceHt); upDown(clearence);
		 */

		// Loop until no more Blocks
		for (int i = 0; i < blockHeights.length; i++) {
			// Raise the arm to 0 Depth
			raise();

			// Move Up or Down according to the Highest Obstacle 
			clearence = clearenceCheck(targetHt, barHeights, sourceHt);
			upDown(clearence);

			// Extend the arm to the Width of the Source Height
			// No argument due to the same Width every iteration
			extend();

			// Lower the arm to just on top of the Source Height
			lower(sourceHt);

			// Pick up the Block
			r.pick();

			// Block Height is acquired
			blockHt = blockHeights[blockIndex];

			// Raise arm to 0 Depth
			raise();

			// If the Source Height is less than the Highest Bar,
			// then move arm Up or Down
			if (sourceHt < clearenceCheck(targetHt, barHeights) + blockHt) {
				// if (i == 0) {
				clearence = clearenceCheck(targetHt, barHeights, sourceHt) + blockHt;
				upDown(clearence);
			}

			// Target Width is given by the Block's Height
			targetWidth = blockHeights[blockIndex];
			System.out.println("w" + w);

			// Contract until Target Width
			contract(targetWidth);

			// After Picking Up a Block, Source Height goes down by the Block's
			// Height
			sourceHt -= blockHt;

			// Lower arm until Target Height
			System.out.println(h + " " + d + " " + " " + blockHt + " " + targetHt[(blockHeights[blockIndex] - 1)] + " "
					+ blockHeights[blockIndex] + " " + blockIndex);
			while (h - d - blockHt > targetHt[(blockHeights[blockIndex]) - 1] + 1) {
				r.lower();
				d++;
			}

			// Target Height is updated, -1 for arm
			targetHt[(blockHeights[blockIndex] - 1)] += blockHeights[blockIndex];

			// Drop block
			r.drop();

			// Move onto next Block
			blockIndex--;
		}

	}
}