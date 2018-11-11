import React from 'react';

import { SearchInput } from '../SearchInput';

export const SearchComponent = ({ className }) => {
  return (
    <section className={className}>
      <SearchInput placeholder="층 선택" title="출발" type="text" />
      <SearchInput placeholder="층 선택" title="도착" type="text" />
    </section>
  );
};
