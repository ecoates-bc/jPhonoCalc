library(ggplot2)
library(ggrepel)

floads <- read.csv("matrixVowels.csv")

row.names(floads) <- floads[, 1]
floads <- floads [, -1]

fit <- cmdscale(floads, eig = TRUE, k = 2)
x <- fit$points[, 1]
y <- fit$points[, 2]

ggplot(floads, aes(x, y, label = row.names(floads))) +
	geom_text_repel() +
	geom_point(color = 'black') +
	theme_classic(base_size = 16)
