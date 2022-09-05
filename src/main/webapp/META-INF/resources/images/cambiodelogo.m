clear all
close all
clc

in = imread("logo.png");

imshow(in);

out = 255-in;

figure();
imshow(out);

imwrite(out, "logonegativo.png");