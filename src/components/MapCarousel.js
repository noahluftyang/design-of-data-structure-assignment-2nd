import Carousel from 'nuka-carousel';
import React, { PureComponent } from 'react';
import styled from 'styled-components';

import { Map } from './Map';

const FLOORS = ['5', '6', '7'];

class MapCarouselComponent extends PureComponent {
  render () {
    return (
      <Carousel className={this.props.className}>
        {FLOORS.map((floor) => <Map name={floor} />)}
      </Carousel>
    );
  }
}

export const MapCarousel = styled(MapCarouselComponent)`
  flex: 1;
`;
