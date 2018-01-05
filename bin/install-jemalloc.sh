wget https://github.com/jemalloc/jemalloc/releases/download/4.0.4/jemalloc-4.0.4.tar.bz2
tar -xf jemalloc-4.0.4.tar.bz2
cd jemalloc-4.0.4
./configure && make && make install
ldconfig

