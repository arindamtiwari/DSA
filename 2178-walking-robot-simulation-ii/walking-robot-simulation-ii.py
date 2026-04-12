class Robot(object):
    def __init__(self, width, height):
        self.w = width
        self.h = height
        # Calculate the total number of steps in one full perimeter loop
        self.p = 2 * (width - 1) + 2 * (height - 1)
        self.pos = 0
        self.started = False

    def step(self, num):
        self.started = True
        # Modulo arithmetic prevents unnecessary loops
        self.pos = (self.pos + num) % self.p

    def getPos(self):
        if self.pos == 0:
            return [0, 0]
        elif self.pos < self.w:
            # Bottom edge
            return [self.pos, 0]
        elif self.pos < self.w + self.h - 1:
            # Right edge
            return [self.w - 1, self.pos - self.w + 1]
        elif self.pos < 2 * self.w + self.h - 2:
            # Top edge
            return [self.w - 1 - (self.pos - (self.w + self.h - 2)), self.h - 1]
        else:
            # Left edge
            return [0, self.h - 1 - (self.pos - (2 * self.w + self.h - 3))]

    def getDir(self):
        if self.pos == 0:
            # If it hasn't moved yet it faces East, otherwise it completed a lap and faces South
            return "South" if self.started else "East"
        elif self.pos < self.w:
            return "East"
        elif self.pos < self.w + self.h - 1:
            return "North"
        elif self.pos < 2 * self.w + self.h - 2:
            return "West"
        else:
            return "South"
            